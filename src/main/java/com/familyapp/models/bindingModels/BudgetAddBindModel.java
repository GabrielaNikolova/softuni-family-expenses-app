package com.familyapp.models.bindingModels;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetAddBindModel {

    private BigDecimal budgetAmount;
    private BigDecimal incomeAmount;
    private BigDecimal savingsAmount;
    private LocalDate receivedOn;


    public BudgetAddBindModel() {
    }

    @DecimalMin("0")
    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    @DecimalMin("0")
    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    @DecimalMin("0")
    public BigDecimal getSavingsAmount() {
        return savingsAmount;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "Date cannot be in the past")
    public LocalDate getReceivedOn() {
        return receivedOn;
    }

    public BudgetAddBindModel setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
        return this;
    }

    public BudgetAddBindModel setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
        return this;
    }

    public BudgetAddBindModel setSavingsAmount(BigDecimal savingsAmount) {
        this.savingsAmount = savingsAmount;
        return this;
    }

    public BudgetAddBindModel setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
        return this;
    }
}
