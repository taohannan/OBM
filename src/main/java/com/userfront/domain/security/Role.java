package com.userfront.domain.security;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Entity
public class Role {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public Role() {
    }

    public int getRoleId() {
       //EFFECTS: return roleId data member value that has been initialized at setRoleId() procedure/method
        return roleId;
    }

    public void setRoleId(int roleId) {
        //REQUIRES: roleId as integer type
        //MODIFIES: this.roleId
        this.roleId = roleId;
    }

    public String getName() {
        //EFFECTS: return name data member value that has been initialized at setName() procedure/method
        return name;
    }

    public void setName(String name) {
        //REQUIRES: name as String type
        //MODIFIES: this.name
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        //EFFECTS: return userRoles data member value that has been initialized at setUserRoles() procedure/method
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        //REQUIRES: userRoles as UserRoles object type
        //MODIFIES: this.userRoles
        this.userRoles = userRoles;
    }


}
