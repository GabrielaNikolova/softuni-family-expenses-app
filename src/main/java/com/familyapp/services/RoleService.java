package com.familyapp.services;

import com.familyapp.models.entities.Role;
import com.familyapp.models.enumModels.RoleEnums;

public interface RoleService {
    void rolesInit();
    Role findByRole(RoleEnums role);
}
