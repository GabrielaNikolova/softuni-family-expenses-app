package com.familyapp.models.serviceModels;

public class FamilyRegistrationServModel {
    private String familyName;
    private String email;

    public FamilyRegistrationServModel() {
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public FamilyRegistrationServModel setFamilyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    public FamilyRegistrationServModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
