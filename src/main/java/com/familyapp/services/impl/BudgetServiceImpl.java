package com.familyapp.services.impl;

import com.familyapp.models.entities.Budget;
import com.familyapp.models.entities.User;
import com.familyapp.repositories.BudgetRepo;
import com.familyapp.services.BudgetService;
import com.familyapp.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepo budgetRepo;
    private final UserService userService;

    public BudgetServiceImpl(BudgetRepo budgetRepo, UserService userService) {
        this.budgetRepo = budgetRepo;
        this.userService = userService;
    }

    @Override
    public void addBudget(BigDecimal newAmount) {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Budget budget = budgetRepo.findByAddedBy_Username(user.getUsername());

        if (budget == null) {
            Budget newBudget = new Budget();
            newBudget
                    .setBudgetAmount(BigDecimal.valueOf(0).add(newAmount))
                    .setEditedOn(LocalDate.now())
                    .setAddedBy(user);

            budgetRepo.save(newBudget);
            user.setPrivateBudget(newBudget);
        } else {
            budget
                    .setBudgetAmount(budget.getBudgetAmount().add(newAmount))
                    .setEditedOn(LocalDate.now())
                    .setAddedBy(user);

            budgetRepo.save(budget);
            user.setPrivateBudget(budget);
        }
    }

    @Override
    public BigDecimal getBudgetByUser() {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Long budgetAmount = budgetRepo.findBudgetAmountByUser(user.getId());

        if (budgetAmount == null) {
            return BigDecimal.valueOf(0);
        }

        return BigDecimal.valueOf(budgetAmount);
    }

    @Override
    public BigDecimal getBudgetByFamily() {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Long budgetAmount = budgetRepo.findBudgetAmountByFamily(user.getFamily().getId());

        if (budgetAmount == null) {
            return BigDecimal.valueOf(0);
        }

        return BigDecimal.valueOf(budgetAmount);
    }

    @Override
    public void subtractExpenseFromBudget(BigDecimal expense, LocalDate expenseDueDate) {
        User user = userService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Budget budget = budgetRepo.findByAddedBy_Username(user.getUsername());

        if (budget == null) {
            Budget newBudget = new Budget();
            newBudget
                    .setBudgetAmount(BigDecimal.valueOf(0).subtract(expense))
                    .setEditedOn(LocalDate.now())
                    .setAddedBy(user);

            budgetRepo.save(newBudget);
            user.setPrivateBudget(newBudget);
        } else {
            budget
                    .setBudgetAmount(budget.getBudgetAmount().subtract(expense))
                    .setEditedOn(LocalDate.now())
                    .setAddedBy(user);

            budgetRepo.save(budget);
            user.setPrivateBudget(budget);
        }
    }

}
