package com.familyapp.models.viewModels;

import com.familyapp.models.enumModels.ExpenseCategoryEnum;
import com.familyapp.models.enumModels.ExpenseTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseViewModel {
    private Long id;
    private String expenseName;
    private ExpenseTypeEnum expenseType;
    private ExpenseCategoryEnum expenseCategory;
    private BigDecimal amount;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdOn;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;
    private String addedFrom;


    public ExpenseViewModel() {
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

    public String getAddedFrom() {
        return addedFrom;
    }

    public ExpenseViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public ExpenseViewModel setExpenseName(String expenseName) {
        this.expenseName = expenseName;
        return this;
    }

    public ExpenseViewModel setExpenseType(ExpenseTypeEnum expenseType) {
        this.expenseType = expenseType;
        return this;
    }

    public ExpenseViewModel setExpenseCategory(ExpenseCategoryEnum expenseCategory) {
        this.expenseCategory = expenseCategory;
        return this;
    }

    public ExpenseViewModel setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public ExpenseViewModel setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public ExpenseViewModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ExpenseViewModel setAddedFrom(String addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }
}
