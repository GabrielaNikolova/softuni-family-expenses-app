package com.familyapp.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
public class Income extends BaseEntity {

    private BigDecimal incomeAmount;
    private LocalDate receivedOn;
    private BigDecimal savingsAmount;
    private User addedBy;

    public Income() {
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

    @ManyToOne(fetch = FetchType.EAGER)
    public User getAddedBy() {
        return addedBy;
    }

    public Income setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
        return this;
    }

    public Income setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
        return this;
    }

    public Income setSavingsAmount(BigDecimal savingsAmount) {
        this.savingsAmount = savingsAmount;
        return this;
    }

    public Income setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
