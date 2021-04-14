package com.familyapp.models.serviceModels;

public class UserLoginServModel {
    private String username;
    private String password;

    public UserLoginServModel() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginServModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserLoginServModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
