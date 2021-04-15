package com.familyapp.services;

import com.familyapp.models.serviceModels.ExpenseAddServModel;

import java.math.BigDecimal;

public interface ExpenseService {
    void addExpense(ExpenseAddServModel expenseAddServiceModel);

    BigDecimal getMonthlyExpensesByUser();
}
