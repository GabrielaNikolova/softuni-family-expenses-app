package com.familyapp.services;

import com.familyapp.models.entities.Family;
import com.familyapp.models.entities.User;
import com.familyapp.models.serviceModels.FamilyRegistrationServModel;

import java.util.Optional;

public interface FamilyService {
    Family findByEmail(String familyEmail);

    boolean emailExists(String familyEmail);

    Family findById(Long id);

    void registerFamily(FamilyRegistrationServModel familyServiceModel);
}
