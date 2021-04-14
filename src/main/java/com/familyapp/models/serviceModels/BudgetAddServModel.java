package com.familyapp.models.serviceModels;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetAddServModel {
    private BigDecimal budgetAmount;
    private BigDecimal incomeAmount;
    private BigDecimal savingsAmount;
    private LocalDate receivedOn;


    public BudgetAddServModel() {
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

    public BudgetAddServModel setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
        return this;
    }

    public BudgetAddServModel setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
        return this;
    }

    public BudgetAddServModel setSavingsAmount(BigDecimal savingsAmount) {
        this.savingsAmount = savingsAmount;
        return this;
    }

    public BudgetAddServModel setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
        return this;
    }
}
