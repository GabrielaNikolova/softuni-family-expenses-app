package com.familyapp.web;

import com.familyapp.services.BudgetService;
import com.familyapp.services.IncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final BudgetService budgetService;
    private final IncomeService incomeService;

    public DashboardController(BudgetService budgetService, IncomeService incomeService) {
        this.budgetService = budgetService;
        this.incomeService = incomeService;
    }


    @GetMapping("/users/dashboard")
    public String dashboardUserShow(Model model) {

        model.addAttribute("userBudget", budgetService.getBudgetByUser());
        model.addAttribute("userIncome", incomeService.getIncomeByUser());
        model.addAttribute("userSavings", incomeService.getSavingsByUser());
        return "dashboard-user";
    }

    @GetMapping("/family/dashboard")
    public String dashboardFamilyShow(){
        return "dashboard-family";
    }
}
