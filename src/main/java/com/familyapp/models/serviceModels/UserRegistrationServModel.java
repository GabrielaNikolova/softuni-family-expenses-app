package com.familyapp.models.serviceModels;

public class UserRegistrationServModel {
    private String username;
    private String fullName;
    private String password;
    private String familyEmail;

    public UserRegistrationServModel() {
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getFamilyEmail() {
        return familyEmail;
    }

    public UserRegistrationServModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRegistrationServModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserRegistrationServModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRegistrationServModel setFamilyEmail(String familyEmail) {
        this.familyEmail = familyEmail;
        return this;
    }
}
