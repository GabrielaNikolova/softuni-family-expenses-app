package com.familyapp.models.viewModels;

import com.familyapp.models.entities.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BudgetViewModel {
    private Long id;
    private BigDecimal budgetAmount;
    private LocalDate editedOn;
    private User addedBy;

    public BudgetViewModel() {
    }


    public Long getId() {
        return id;
    }

    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    public LocalDate getReceivedOn() {
        return editedOn;
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

    public BudgetViewModel setReceivedOn(LocalDate editedOn) {
        this.editedOn = editedOn;
        return this;
    }

    public BudgetViewModel setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
