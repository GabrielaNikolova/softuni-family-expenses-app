package com.familyapp.models.serviceModels;

import com.familyapp.models.entities.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeAddServModel {
    private Long id;
    private BigDecimal incomeAmount;
    private BigDecimal savingsAmount;
    private LocalDate receivedOn;
    private User addedBy;


    public IncomeAddServModel() {
    }

    public Long getId() {
        return id;
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

    public IncomeAddServModel setId(Long id) {
        this.id = id;
        return this;
    }

    public IncomeAddServModel setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }

    public IncomeAddServModel setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
        return this;
    }

    public IncomeAddServModel setSavingsAmount(BigDecimal savingsAmount) {
        this.savingsAmount = savingsAmount;
        return this;
    }

    public IncomeAddServModel setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
        return this;
    }
}
