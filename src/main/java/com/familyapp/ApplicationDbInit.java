package com.familyapp;

import com.familyapp.services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ApplicationDbInit implements CommandLineRunner {
    private final RoleService roleService;

    public ApplicationDbInit(RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public void run(String... args) throws Exception {
        roleService.rolesInit();

    }
}
