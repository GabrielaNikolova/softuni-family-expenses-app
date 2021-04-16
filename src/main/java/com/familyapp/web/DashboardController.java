package com.familyapp.web;

import com.familyapp.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final BudgetService budgetService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final UserService userService;
    private final EventService eventService;

    public DashboardController(BudgetService budgetService, IncomeService incomeService, ExpenseService expenseService, UserService userService, EventService eventService) {
        this.budgetService = budgetService;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        this.userService = userService;
        this.eventService = eventService;
    }


    @GetMapping("/users/dashboard")
    public String dashboardUserShow(Model model) {

        model.addAttribute("userBudget", budgetService.getBudgetByUser());
        model.addAttribute("userIncome", incomeService.getMonthlyIncomeByUser());
        model.addAttribute("userExpenses", expenseService.getMonthlyExpensesByUser());
        model.addAttribute("userSavings", incomeService.getSavingsByUser());
        model.addAttribute("monthlyExpenses", expenseService.getAllByUser());
        model.addAttribute("userEvents", eventService.getAllByUser());
        return "dashboard-user";
    }

    @GetMapping("/family/dashboard")
    public String dashboardFamilyShow(Model model) {

        model.addAttribute("familyBudget", budgetService.getBudgetByFamily());
        model.addAttribute("familyIncome", incomeService.getMonthlyIncomeByFamily());
        model.addAttribute("familyExpenses", expenseService.getMonthlyExpensesByFamily());
        model.addAttribute("familySavings", incomeService.getSavingsByFamily());
        model.addAttribute("monthlyFamilyExpenses", expenseService.getAllByFamily());
        model.addAttribute("familyMembers", userService.getAllFamilyMembers());
        model.addAttribute("familyEvents", eventService.getAllByFamily());
        return "dashboard-family";
    }
}
