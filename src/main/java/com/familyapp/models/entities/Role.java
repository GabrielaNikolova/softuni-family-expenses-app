package com.familyapp.models.entities;

import com.familyapp.models.enumModels.RoleEnums;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    private RoleEnums name;
    public Role() {
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    public RoleEnums getName() {
        return name;
    }

    public Role setName(RoleEnums name) {
        this.name = name;
        return this;
    }
}
