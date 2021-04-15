package com.familyapp.services;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BudgetService {
    void addBudget(BigDecimal newAmount);

    BigDecimal getBudgetByUser();

    void subtractExpenseFromBudget(BigDecimal expense, LocalDate expenseDueDate);


    //List<BudgetViewModel> findAllByFamily();
}
