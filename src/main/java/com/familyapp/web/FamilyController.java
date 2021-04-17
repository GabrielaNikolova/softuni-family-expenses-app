package com.familyapp.web;

import com.familyapp.models.bindingModels.FamilyLoginBindModel;
import com.familyapp.models.bindingModels.FamilyRegistrationBindModel;
import com.familyapp.models.bindingModels.UserRegistrationBindModel;
import com.familyapp.models.serviceModels.FamilyRegistrationServModel;
import com.familyapp.services.FamilyService;
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
@RequestMapping("/families")
public class FamilyController {

    private final ModelMapper modelMapper;
    private final FamilyService familyService;


    public FamilyController(ModelMapper modelMapper, FamilyService familyService) {
        this.modelMapper = modelMapper;
        this.familyService = familyService;
    }


    @ModelAttribute("familyRegistrationBindModel")
    public FamilyRegistrationBindModel createBindModel() {
        return new FamilyRegistrationBindModel();
    }

    @ModelAttribute("familyLoginBindModel")
    public FamilyLoginBindModel createLoginBindModel() {
        return new FamilyLoginBindModel();
    }


    @GetMapping("/register")
    public String register() {
        return "register-family";
    }

    @GetMapping("/confirm")
    public String confirmFamily() {
        return "family-confirm";
    }


    @PostMapping("/confirm")
    public String registerAndLoginUser(@Valid FamilyLoginBindModel familyLoginBindModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("familyLoginBindModel", familyLoginBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.familyLoginBindModel", bindingResult);
            return "redirect:/families/confirm";
        }

        if (!familyService.emailExists(familyLoginBindModel.getFamilyEmail())) {
            redirectAttributes.addFlashAttribute("familyLoginBindModel", familyLoginBindModel);
            redirectAttributes.addFlashAttribute("familyDoesntExistsError", true);
            return "redirect:/families/confirm";
        }

        UserRegistrationBindModel userRegistrationBindModel = new UserRegistrationBindModel();
        userRegistrationBindModel.setFamilyEmail(familyLoginBindModel.getFamilyEmail());
        redirectAttributes.addFlashAttribute("userRegistrationBindModel", userRegistrationBindModel);
        redirectAttributes.addFlashAttribute("familyConfirmed", true);

        return "redirect:/users/register";
    }


    @PostMapping("/register")
    public String registerFamily(
            @Valid FamilyRegistrationBindModel familyRegistrationBindModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("familyRegistrationBindModel", familyRegistrationBindModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.familyRegistrationBindModel", bindingResult);

            return "redirect:/families/register";
        }

        if (familyService.emailExists(familyRegistrationBindModel.getEmail())) {
            redirectAttributes.addFlashAttribute("familyRegistrationBindModel", familyRegistrationBindModel);
            redirectAttributes.addFlashAttribute("familyExistsError", true);

            return "redirect:/families/register";
        }

        FamilyRegistrationServModel familyServiceModel = modelMapper
                .map(familyRegistrationBindModel, FamilyRegistrationServModel.class);

        familyService.registerFamily(familyServiceModel);

        return "redirect:/families/confirm";
    }


}
