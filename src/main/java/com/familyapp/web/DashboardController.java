package com.familyapp.web;

import com.familyapp.services.BudgetService;
import com.familyapp.services.ExpenseService;
import com.familyapp.services.IncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final BudgetService budgetService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    public DashboardController(BudgetService budgetService, IncomeService incomeService, ExpenseService expenseService) {
        this.budgetService = budgetService;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }


    @GetMapping("/users/dashboard")
    public String dashboardUserShow(Model model) {

        model.addAttribute("userBudget", budgetService.getBudgetByUser());
        model.addAttribute("userIncome", incomeService.getMonthlyIncomeByUser());
        model.addAttribute("userExpenses", expenseService.getMonthlyExpensesByUser());
        model.addAttribute("userSavings", incomeService.getSavingsByUser());
        model.addAttribute("monthlyExpenses", expenseService.getAllByUser());
        return "dashboard-user";
    }

    @GetMapping("/family/dashboard")
    public String dashboardFamilyShow(Model model) {

        model.addAttribute("familyBudget", budgetService.getBudgetByFamily());
        model.addAttribute("familyIncome", incomeService.getMonthlyIncomeByFamily());
        model.addAttribute("familyExpenses", expenseService.getMonthlyExpensesByFamily());
        model.addAttribute("familySavings", incomeService.getSavingsByFamily());
        return "dashboard-family";
    }
}
