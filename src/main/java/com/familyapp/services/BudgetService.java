package com.familyapp.services;

import com.familyapp.models.serviceModels.BudgetAddServModel;
import com.familyapp.models.viewModels.BudgetViewModel;

import java.util.List;

public interface BudgetService {
    void addBudget(BudgetAddServModel budgetAddServiceModel);

    List<BudgetViewModel> findAllByUser();
    BudgetViewModel findByMonth();
    //List<BudgetViewModel> findAllByFamily();
}
