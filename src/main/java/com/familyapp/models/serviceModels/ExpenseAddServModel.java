package com.familyapp.models.serviceModels;

import com.familyapp.models.entities.User;
import com.familyapp.models.enumModels.ExpenseCategoryEnum;
import com.familyapp.models.enumModels.ExpenseTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseAddServModel {
    private Long id;
    private String expenseName;
    private ExpenseTypeEnum expenseType;
    private ExpenseCategoryEnum expenseCategory;
    private BigDecimal amount;
    private LocalDate createdOn;
    private LocalDate dueDate;
    //private String belongTo;
    private User addedFrom;

    public ExpenseAddServModel() {
    }


    public Long getId() {
        return id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public ExpenseTypeEnum getExpenseType() {
        return expenseType;
    }

    public ExpenseCategoryEnum getExpenseCategory() {
        return expenseCategory;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public User getAddedFrom() {
        return addedFrom;
    }

    public ExpenseAddServModel setId(Long id) {
        this.id = id;
        return this;
    }

    public ExpenseAddServModel setExpenseName(String expenseName) {
        this.expenseName = expenseName;
        return this;
    }

    public ExpenseAddServModel setExpenseType(ExpenseTypeEnum expenseType) {
        this.expenseType = expenseType;
        return this;
    }

    public ExpenseAddServModel setExpenseCategory(ExpenseCategoryEnum expenseCategory) {
        this.expenseCategory = expenseCategory;
        return this;
    }

    public ExpenseAddServModel setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public ExpenseAddServModel setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public ExpenseAddServModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ExpenseAddServModel setAddedFrom(User addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }
}
