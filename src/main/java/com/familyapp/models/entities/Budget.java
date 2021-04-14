package com.familyapp.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "budget")
public class Budget extends BaseEntity{
    private BigDecimal budgetAmount;
    private BigDecimal incomeAmount;
    private BigDecimal savingsAmount;
    private LocalDate receivedOn;
    private User addedBy;



    public Budget() {
    }

    @Column(name = "budget_amount", nullable = false)
    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }
    @Column(name = "income_amount", nullable = false)
    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }
    @Column(name = "savings_amount", nullable = false)
    public BigDecimal getSavingsAmount() {
        return savingsAmount;
    }

    @Column(name = "received_on", nullable = false)
    public LocalDate getReceivedOn() {
        return receivedOn;
    }

    @ManyToOne
    public User getAddedBy() {
        return addedBy;
    }

    public Budget setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }

    public Budget setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
        return this;
    }

    public Budget setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
        return this;
    }

    public Budget setSavingsAmount(BigDecimal savingsAmount) {
        this.savingsAmount = savingsAmount;
        return this;
    }

    public Budget setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
        return this;
    }
}
