package com.familyapp.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "budget")
public class Budget extends BaseEntity{
    private BigDecimal budgetAmount;
    private LocalDate editedOn;
    private User addedBy;


    public Budget() {
    }

    @Column(name = "budget_amount", nullable = false)
    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    @Column(name = "edited_on", nullable = false)
    public LocalDate getEditedOn() {
        return editedOn;
    }

    @OneToOne
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

    public Budget setEditedOn(LocalDate editedOn) {
        this.editedOn = editedOn;
        return this;
    }
}
