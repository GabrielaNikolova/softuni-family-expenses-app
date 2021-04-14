package com.familyapp.services;

import java.math.BigDecimal;

public interface BudgetService {
    void addBudget(BigDecimal newAmount);

    BigDecimal getBudgetByUser();

    //List<BudgetViewModel> findAllByFamily();
}
