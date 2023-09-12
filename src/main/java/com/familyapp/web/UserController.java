package com.familyapp.web;

import com.familyapp.models.bindingModels.UserRegistrationBindModel;
import com.familyapp.models.serviceModels.UserRegistrationServModel;
import com.familyapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    //private final FamilyService familyService;


    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        //this.familyService = familyService;
    }


    @ModelAttribute("userRegistrationBindModel")
    public UserRegistrationBindModel createBindModel(UserRegistrationBindModel user) {
        return new UserRegistrationBindModel().setFamilyEmail(user.getFamilyEmail());
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String registerAndLoginUser(
            @Valid UserRegistrationBindModel userRegistrationBindModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationBindModel", userRegistrationBindModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegistrationBindModel", bindingResult);

            return "redirect:/users/register";
        }

        if (userService.userNameExists(userRegistrationBindModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindModel", userRegistrationBindModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

            return "redirect:/users/register";
        }

        UserRegistrationServModel userServiceModel = modelMapper
                .map(userRegistrationBindModel, UserRegistrationServModel.class);

        userService.registerAndLoginUser(userServiceModel);

        return "redirect:/users/dashboard";
    }


    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username,
                              RedirectAttributes attributes) {

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", username);

        return "redirect:/users/login";
    }


}
