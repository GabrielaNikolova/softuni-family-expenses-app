package com.familyapp.services;

import com.familyapp.models.serviceModels.IncomeAddServModel;

import java.math.BigDecimal;

public interface IncomeService {
    void addIncome(IncomeAddServModel incomeAddServiceModel);

    BigDecimal getIncomeByUser();

    BigDecimal getSavingsByUser();
}
