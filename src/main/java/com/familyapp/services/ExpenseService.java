package com.familyapp.services;

import com.familyapp.models.serviceModels.ExpenseAddServModel;
import com.familyapp.models.viewModels.ExpenseViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {
    void addExpense(ExpenseAddServModel expenseAddServiceModel);

    BigDecimal getMonthlyExpensesByUser();

    List<ExpenseViewModel> getAllByUser();

    List<ExpenseViewModel> getAllByUserAndType(String expenseType);

    void deleteExpenseById(String id);

    ExpenseViewModel getExpenseById(String id);
}
