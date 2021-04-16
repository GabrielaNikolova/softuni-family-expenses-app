package com.familyapp.models.bindingModels;

import com.familyapp.models.enumModels.ExpenseCategoryEnum;
import com.familyapp.models.enumModels.ExpenseTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseAddBindModel {
    private String expenseName;
    private ExpenseTypeEnum expenseType;
    private ExpenseCategoryEnum expenseCategory;
    private BigDecimal amount;
    private LocalDate createdOn;
    private LocalDate dueDate;


    public ExpenseAddBindModel() {
    }

    @NotEmpty
    @Size(min = 3, max = 20, message = "Expense name length must be between 3 and 20 characters")
    public String getExpenseName() {
        return expenseName;
    }

    @NotNull(message = "You must select expense type")
    public ExpenseTypeEnum getExpenseType() {
        return expenseType;
    }

    @NotNull(message = "You must select expense category")
    public ExpenseCategoryEnum getExpenseCategory() {
        return expenseCategory;
    }

    @DecimalMin("0")
    public BigDecimal getAmount() {
        return amount;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Date cannot be in the past")
    public LocalDate getCreatedOn() {
        return createdOn;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Date cannot be in the past")
    public LocalDate getDueDate() {
        return dueDate;
    }


    public ExpenseAddBindModel setExpenseName(String expenseName) {
        this.expenseName = expenseName;
        return this;
    }

    public ExpenseAddBindModel setExpenseType(ExpenseTypeEnum expenseType) {
        this.expenseType = expenseType;
        return this;
    }

    public ExpenseAddBindModel setExpenseCategory(ExpenseCategoryEnum expenseCategory) {
        this.expenseCategory = expenseCategory;
        return this;
    }

    public ExpenseAddBindModel setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public ExpenseAddBindModel setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public ExpenseAddBindModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

}
