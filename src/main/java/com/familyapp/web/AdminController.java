package com.familyapp.web;

import com.familyapp.models.enumModels.RoleEnums;
import com.familyapp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/stats")
    public String getStats(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "admin-stats";
    }

    @GetMapping("/user-roles")
    public String add(Model model) {
        model.addAttribute("names", userService.getAllUsernames());
        return "admin-roles";
    }


    @PostMapping("/user-roles")
    public String addConfirm(@RequestParam String username,
                             @RequestParam String role) {

        userService.changeUserRole(username, RoleEnums.valueOf(role.toUpperCase()));

        return "redirect:/admin/stats";
    }
}
