package com.familyapp.services.impl;

import com.familyapp.models.entities.Expense;
import com.familyapp.models.entities.User;
import com.familyapp.models.enumModels.ExpenseTypeEnum;
import com.familyapp.models.serviceModels.ExpenseAddServModel;
import com.familyapp.models.viewModels.ExpenseViewModel;
import com.familyapp.repositories.ExpenseRepo;
import com.familyapp.services.BudgetService;
import com.familyapp.services.ExpenseService;
import com.familyapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final UserService userService;
    private final BudgetService budgetService;
    private final ModelMapper modelMapper;

    public ExpenseServiceImpl(ExpenseRepo expenseRepo, UserService userService, BudgetService budgetService, ModelMapper modelMapper) {
        this.expenseRepo = expenseRepo;
        this.userService = userService;
        this.budgetService = budgetService;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public void addExpense(ExpenseAddServModel expenseAddServiceModel) {
        Expense newExpense = modelMapper.map(expenseAddServiceModel, Expense.class);
        newExpense.setCreatedOn(LocalDate.now());

        User user = userService
                .findByName(SecurityContextHolder.getContext().getAuthentication().getName());

        newExpense.setAddedFrom(user);
        expenseRepo.save(newExpense);

        if (user.getExpenses() == null) {
            List<Expense> expenses = new ArrayList<>();
            expenses.add(newExpense);
            user.setExpenses(expenses);
        } else {
            user.getExpenses().add(newExpense);
        }

        budgetService.subtractExpenseFromBudget(newExpense.getAmount(), newExpense.getDueDate());

    }

    @Override
    public BigDecimal getMonthlyExpensesByUser() {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Long expensesAmount = expenseRepo.findExpensesByUser(user.getId());

        if (expensesAmount == null) {
            return BigDecimal.valueOf(0);
        }

        return BigDecimal.valueOf(expensesAmount);
    }

    @Override
    public BigDecimal getMonthlyExpensesByFamily() {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Long expensesAmount = expenseRepo.findExpensesByFamily(user.getFamily().getId());

        if (expensesAmount == null) {
            return BigDecimal.valueOf(0);
        }

        return BigDecimal.valueOf(expensesAmount);
    }

    @Override
    public List<ExpenseViewModel> getAllByUser() {
        List<Expense> expenses = expenseRepo.findAllByAddedFrom_Username(SecurityContextHolder.getContext().getAuthentication().getName());

        if (expenses.isEmpty()) {
            return new ArrayList<>();
        }

        return expenses.stream()
                .map(e -> modelMapper.map(e, ExpenseViewModel.class))
                .filter(e -> e.getCreatedOn().getMonth().equals(LocalDate.now().getMonth()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseViewModel> getAllByUserAndType(String expenseType) {
        List<Expense> expensesByType = expenseRepo.findAllByAddedFrom_UsernameAndExpenseTypeOrderByExpenseCategory(SecurityContextHolder.getContext().getAuthentication().getName(), ExpenseTypeEnum.valueOf(expenseType));

        if (expensesByType.isEmpty()) {
            return new ArrayList<>();
        }

        return expensesByType.stream()
                .map(e -> modelMapper.map(e, ExpenseViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteExpenseById(String id) {
        Optional<Expense> expense = expenseRepo.findById(Long.valueOf(id));

        if (expense.isPresent()) {
            budgetService.addBudget(expense.get().getAmount());
            userService.updateUserExpenses(Long.valueOf(id));
            expenseRepo.deleteById(Long.valueOf(id));
        }
    }

    @Override
    public ExpenseViewModel getExpenseById(String id) {
        Expense expense = expenseRepo.findById(Long.valueOf(id)).orElseThrow(IllegalArgumentException::new);
        ExpenseViewModel expenseView = modelMapper.map(expense, ExpenseViewModel.class);
        expenseView.setAddedFrom(SecurityContextHolder.getContext().getAuthentication().getName());
        return expenseView;
    }

    @Override
    public List<ExpenseViewModel> getAllByFamily() {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Expense> expenses = expenseRepo.findAllByAddedFrom_Family_Id(user.getFamily().getId());

        if (expenses.isEmpty()) {
            return new ArrayList<>();
        }

        return expenses.stream()
                .map(e -> modelMapper.map(e, ExpenseViewModel.class))
                .filter(e -> e.getCreatedOn().getMonth().equals(LocalDate.now().getMonth()))
                .collect(Collectors.toList());
    }
}
