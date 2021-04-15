package com.familyapp.services;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BudgetService {
    void addBudget(BigDecimal newAmount);

    BigDecimal getBudgetByUser();

    BigDecimal getBudgetByFamily();

    void subtractExpenseFromBudget(BigDecimal expense, LocalDate expenseDueDate);


    //List<BudgetViewModel> findAllByFamily();
}
