package com.familyapp.web;

import com.familyapp.services.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class DashboardController {
    private final BudgetService budgetService;

    public DashboardController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }


    @GetMapping("/users/dashboard")
    public String dashboardUserShow(Model model){


//        model.addAttribute("userBudget", budgetService.findAllByUser()
        return "dashboard-user";
    }

    @GetMapping("/family/dashboard")
    public String dashboardFamilyShow(){
        return "dashboard-family";
    }
}
