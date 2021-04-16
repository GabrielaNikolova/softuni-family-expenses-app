package com.familyapp.web;

import com.familyapp.models.bindingModels.ExpenseAddBindModel;
import com.familyapp.models.serviceModels.ExpenseAddServModel;
import com.familyapp.services.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ModelMapper modelMapper;
    private final ExpenseService expenseService;


    public ExpenseController(ModelMapper modelMapper, ExpenseService expenseService) {
        this.modelMapper = modelMapper;
        this.expenseService = expenseService;
    }

    @ModelAttribute("expenseAddBindModel")
    public ExpenseAddBindModel createBindingModel() {
        return new ExpenseAddBindModel();
    }


    @GetMapping("/add")
    public String expenseAddShow() {
        return "expenses-add";
    }

    @GetMapping("/all")
    public String expenseAll(Model model) {

        model.addAttribute("fixedExpenses", expenseService.getAllByUserAndType("FIXED"));
        model.addAttribute("periodicExpenses", expenseService.getAllByUserAndType("PERIODIC"));
        model.addAttribute("variableExpenses", expenseService.getAllByUserAndType("VARIABLE"));

        return "expenses";
    }


    @PostMapping("/add")
    public String addIncome(@Valid ExpenseAddBindModel expenseAddBindModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("expenseAddBindModel", expenseAddBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.expenseAddBindModel", bindingResult);

            return "redirect:add";
        }

        ExpenseAddServModel expenseAddServiceModel = modelMapper
                .map(expenseAddBindModel, ExpenseAddServModel.class);

        expenseService.addExpense(expenseAddServiceModel);

        return "redirect:/expenses/all";
    }


    @GetMapping("/details/{id}")
    public String expenseDetails(@PathVariable String id, Model model) {

        model.addAttribute("expenseDetails", expenseService.getExpenseById(id));
        return "expenses-details";
    }


    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        expenseService.deleteExpenseById(id);
        return "redirect:/expenses/all";
    }


}
