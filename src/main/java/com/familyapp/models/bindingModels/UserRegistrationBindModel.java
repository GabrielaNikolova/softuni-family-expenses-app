package com.familyapp.models.bindingModels;

import com.familyapp.models.validators.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword"
)

public class UserRegistrationBindModel {
    private String username;
    private String fullName;
    private String password;
    private String confirmPassword;
    private String familyEmail;


    public UserRegistrationBindModel() {
    }


    @NotEmpty
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    public String getUsername() {
        return username;
    }


    @NotEmpty
    @Size(min = 3, max = 30, message = "Full Name length must be between 3 and 30 characters")
    public String getFullName() {
        return fullName;
    }


    @NotBlank(message = "Must be a valid email.")
    @Email(message = "Must be a valid email.")
    public String getFamilyEmail() {
        return familyEmail;
    }


    @NotEmpty
    @Size(min = 5, max = 20)
    public String getPassword() {
        return password;
    }


    @NotEmpty
    @Size(min = 5, max = 20)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRegistrationBindModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserRegistrationBindModel setFamilyEmail(String familyEmail) {
        this.familyEmail = familyEmail;
        return this;
    }

    public UserRegistrationBindModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRegistrationBindModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }


}
