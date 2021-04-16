package com.familyapp.models.viewModels;

import java.util.List;

public class UserViewModel {
    private Long id;
    private String username;
    private String fullName;
    private List<String> roles;

    public UserViewModel() {
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserViewModel setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
