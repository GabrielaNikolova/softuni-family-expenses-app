package com.familyapp.web;

import com.familyapp.models.bindingModels.BudgetAddBindModel;
import com.familyapp.models.serviceModels.BudgetAddServModel;
import com.familyapp.services.BudgetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    private final ModelMapper modelMapper;
    private final BudgetService budgetService;

    public BudgetController(ModelMapper modelMapper, BudgetService budgetService) {
        this.modelMapper = modelMapper;
        this.budgetService = budgetService;
    }

    @ModelAttribute("budgetAddBindModel")
    public BudgetAddBindModel createBindingModel() {
        return new BudgetAddBindModel();
    }


    @GetMapping("/add")
    public String budgetAddShow() {

//        model.addAttribute("artists",
//                artistService.findAllArtists());
        return "budget-add";
    }


    @PostMapping("/add")
    public String addBudget(@Valid BudgetAddBindModel budgetAddBindModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("budgetAddBindModel", budgetAddBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.budgetAddBindModel", bindingResult);

            return "redirect:add";
        }

        BudgetAddServModel budgetAddServiceModel = modelMapper
                .map(budgetAddBindModel, BudgetAddServModel.class);

        budgetService.addBudget(budgetAddServiceModel);


        return "redirect:/users/dashboard";
    }


}
