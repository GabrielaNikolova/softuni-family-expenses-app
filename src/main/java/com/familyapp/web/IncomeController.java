package com.familyapp.web;

import com.familyapp.models.bindingModels.IncomeAddBindModel;
import com.familyapp.models.serviceModels.IncomeAddServModel;
import com.familyapp.services.IncomeService;
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
@RequestMapping("/income")
public class IncomeController {

    private final ModelMapper modelMapper;
    private final IncomeService incomeService;

    public IncomeController(ModelMapper modelMapper, IncomeService incomeService) {
        this.modelMapper = modelMapper;
        this.incomeService = incomeService;
    }

    @ModelAttribute("incomeAddBindModel")
    public IncomeAddBindModel createBindingModel() {
        return new IncomeAddBindModel();
    }


    @GetMapping("/add")
    public String incomeAddShow() {
        return "income-add";
    }


    @PostMapping("/add")
    public String addIncome(@Valid IncomeAddBindModel incomeAddBindModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("incomeAddBindModel", incomeAddBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.incomeAddBindModel", bindingResult);

            return "redirect:add";
        }

        if (incomeAddBindModel.getIncomeAmount().compareTo(incomeAddBindModel.getSavingsAmount()) < 0) {
            redirectAttributes.addFlashAttribute("incomeAddBindModel", incomeAddBindModel);
            redirectAttributes.addFlashAttribute("invalidSavingsValue", true);

            return "redirect:add";
        }

        IncomeAddServModel incomeAddServiceModel = modelMapper
                .map(incomeAddBindModel, IncomeAddServModel.class);

        incomeService.addIncome(incomeAddServiceModel);

        return "redirect:/users/dashboard";
    }


}
