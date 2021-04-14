package com.familyapp.models.viewModels;

import com.familyapp.models.entities.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetViewModel {
    private Long id;
    private BigDecimal budgetAmount;
    private BigDecimal incomeAmount;
    private BigDecimal savingsAmount;
    private LocalDate receivedOn;
    private User addedBy;

    public BudgetViewModel() {
    }


    public Long getId() {
        return id;
    }

    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public BigDecimal getSavingsAmount() {
        return savingsAmount;
    }

    public LocalDate getReceivedOn() {
        return receivedOn;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public BudgetViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public BudgetViewModel setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
        return this;
    }

    public BudgetViewModel setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
        return this;
    }

    public BudgetViewModel setSavingsAmount(BigDecimal savingsAmount) {
        this.savingsAmount = savingsAmount;
        return this;
    }

    public BudgetViewModel setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
        return this;
    }

    public BudgetViewModel setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
