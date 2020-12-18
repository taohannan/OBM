package com.userfront.domain.security;

import com.userfront.domain.User;

import javax.persistence.*;



@Entity
@Table(name="user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole() {}

    public long getUserRoleId() {
        //EFFECTS: return userRoleId data member value that has been initialized at setUserRoleId() procedure/method
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        //REQUIRES: userRoleId as long type
        //MODIFIES: this.userRoleId
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        //EFFECTS: return user data member value that has been initialized at setUser() procedure/method
        return user;
    }

    public void setUser(User user) {
        //REQUIRES: user as User object type
        //MODIFIES: this.user
        this.user = user;
    }

    public Role getRole() {
        //EFFECTS: return role data member value that has been initialized at setRole() procedure/method
        return role;
    }

    public void setRole(Role role) {
        //REQUIRES: role as Role object type
        //MODIFIES: this.role
        this.role = role;
    }


}
