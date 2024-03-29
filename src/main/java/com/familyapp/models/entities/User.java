package com.familyapp.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String fullName;
    //private int age;
    private Budget privateBudget;
    private List<Income> privateIncomes;
    private List<Role> roles;
    private List<Expense> expenses;
    private List<Event> events;
    private Family family;


    public User() {
    }

    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

//    @Column(name = "age", nullable = false)
//    public int getAge() {
//        return age;
//    }


    @OneToOne
    public Budget getPrivateBudget() {
        return privateBudget;
    }

    @OneToMany
    public List<Income> getPrivateIncomes() {
        return privateIncomes;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Role> getRoles() {
        return roles;
    }

    @OneToMany
    public List<Expense> getExpenses() {
        return expenses;
    }

    @OneToMany
    public List<Event> getEvents() {
        return events;
    }


    @ManyToOne
    public Family getFamily() {
        return family;
    }

    public User setFamily(Family family) {
        this.family = family;
        return this;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

//    public User setAge(int age) {
//        this.age = age;
//        return this;
//    }


    public User setPrivateBudget(Budget privateBudget) {
        this.privateBudget = privateBudget;
        return this;
    }

    public User setPrivateIncomes(List<Income> privateIncomes) {
        this.privateIncomes = privateIncomes;
        return this;
    }

    public User setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User addRole(Role roleEntity) {
        this.roles.add(roleEntity);
        return this;
    }

    public User setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        return this;
    }

    public User setEvents(List<Event> events) {
        this.events = events;
        return this;
    }
}
