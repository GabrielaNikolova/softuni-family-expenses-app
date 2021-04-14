package com.familyapp.models.bindingModels;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class FamilyLoginBindModel {

    private String familyEmail;

    public FamilyLoginBindModel() {
    }

    @NotBlank(message = "Must be a valid email.")
    @Email(message = "Must be a valid email.")
    public String getFamilyEmail() {
        return familyEmail;
    }

    public FamilyLoginBindModel setFamilyEmail(String familyEmail) {
        this.familyEmail = familyEmail;
        return this;
    }
}
