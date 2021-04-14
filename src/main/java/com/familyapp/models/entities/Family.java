package com.familyapp.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "families")
public class Family extends BaseEntity {
    private String familyName;
    private String email;
    private List<User> familyMembers;


    //TODO should I create family budget & expenses

//    private List<Budget> familyBudget;
//    private List<Expense> familyExpenses;


    public Family() {
    }


    @Column(name = "family_name", nullable = false)
    public String getFamilyName() {
        return familyName;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }


    @OneToMany
    public List<User> getFamilyMembers() {
        return familyMembers;
    }

    public Family setFamilyMembers(List<User> familyMembers) {
        this.familyMembers = familyMembers;
        return this;
    }

    public Family setFamilyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    public Family setEmail(String email) {
        this.email = email;
        return this;
    }
}
