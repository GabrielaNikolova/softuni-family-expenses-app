package com.familyapp.services.impl;

import com.familyapp.models.entities.Expense;
import com.familyapp.models.entities.User;
import com.familyapp.models.serviceModels.ExpenseAddServModel;
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
}
