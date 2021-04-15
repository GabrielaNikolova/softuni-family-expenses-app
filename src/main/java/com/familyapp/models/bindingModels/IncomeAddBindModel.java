package com.familyapp.models.bindingModels;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeAddBindModel {

    private BigDecimal incomeAmount;
    private BigDecimal savingsAmount;
    private LocalDate receivedOn;


    public IncomeAddBindModel() {
    }

    @DecimalMin("0")
    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    @DecimalMin("0")
    public BigDecimal getSavingsAmount() {
        return savingsAmount;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Date cannot be in the past")
    public LocalDate getReceivedOn() {
        return receivedOn;
    }

    public IncomeAddBindModel setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
        return this;
    }

    public IncomeAddBindModel setSavingsAmount(BigDecimal savingsAmount) {
        this.savingsAmount = savingsAmount;
        return this;
    }

    public IncomeAddBindModel setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
        return this;
    }
}
