package com.familyapp.models.bindingModels;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class FamilyRegistrationBindModel {
    private String familyName;
    private String email;

    public FamilyRegistrationBindModel() {
    }


    @NotEmpty
    @Size(min = 3, max = 30, message = "Family Name length must be between 3 and 30 characters")
    public String getFamilyName() {
        return familyName;
    }

    @NotBlank(message = "Must be a valid email.")
    @Email(message = "Must be a valid email.")
    public String getEmail() {
        return email;
    }

    public FamilyRegistrationBindModel setFamilyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    public FamilyRegistrationBindModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
