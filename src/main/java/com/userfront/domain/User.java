package com.userfront.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userfront.domain.security.Authority;
import com.userfront.domain.security.UserRole;

@Entity
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String phone;

    private boolean enabled=true;

    @OneToOne
    private PrimaryAccount primaryAccount;

    @OneToOne
    private SavingsAccount savingsAccount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recipient> recipientList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    public Set<UserRole> getUserRoles() {
        //EFFECTS: return userRoles data member value that has been initialized at setUserRoles() procedure/method
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        //REQUIRES: userRoles as UserRoles object type
        //MODIFIES: this.userRoles
        this.userRoles = userRoles;
    }

    public Long getUserId() {
        //EFFECTS: return userId data member value that has been initialized at setUserId() procedure/method
        return userId;
    }

    public void setUserId(Long userId) {
        //REQUIRES: userId as Long type
        //MODIFIES: this.userId
        this.userId = userId;
    }

    public String getUsername() {
        //EFFECTS: return username data member value that has been initialized at setUsername() procedure/method
        return username;
    }

    public void setUsername(String username) {
        //REQUIRES: username as String type
        //MODIFIES: this.username
        this.username = username;
    }

    public String getFirstName() {
        //EFFECTS: return firstName data member value that has been initialized at setFirstName() procedure/method
        return firstName;
    }

    public void setFirstName(String firstName) {
        //REQUIRES: firstName as String type
        //MODIFIES: this.firstName
        this.firstName = firstName;
    }

    public String getLastName() {
        //EFFECTS: return lastName data member value that has been initialized at setLastName() procedure/method
        return lastName;
    }

    public void setLastName(String lastName) {
        //REQUIRES: lastName as String type
        //MODIFIES: this.lastName
        this.lastName = lastName;
    }

    public String getEmail() {
        //EFFECTS: return email data member value that has been initialized at setEmail() procedure/method
        return email;
    }

    public void setEmail(String email) {
        //REQUIRES: email as String type
        //MODIFIES: this.email
        this.email = email;
    }

    public String getPhone() {
        //EFFECTS: return phone data member value that has been initialized at setPhone() procedure/method
        return phone;
    }

    public void setPhone(String phone) {
        //REQUIRES: phone as String type
        //MODIFIES: this.phone
        this.phone = phone;
    }

    public List<Appointment> getAppointmentList() {
        //EFFECTS: return appointmentList data member value that has been initialized at setAppointmentList() procedure/method
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        //REQUIRES: appointmentList as List type
        //MODIFIES: this.appointmentList
        this.appointmentList = appointmentList;
    }

    public List<Recipient> getRecipientList() {
        //EFFECTS: return recipientList data member value that has been initialized at setRecipientList() procedure/method
        return recipientList;
    }

    public void setRecipientList(List<Recipient> recipientList) {
        //REQUIRES: recipientList as List type
        //MODIFIES: this.recipientList
        this.recipientList = recipientList;
    }

    public String getPassword() {
        //EFFECTS: return password data member value that has been initialized at setPassword() procedure/method
        return password;
    }

    public void setPassword(String password) {
        //REQUIRES: password as String type
        //MODIFIES: this.password
        this.password = password;
    }

    public PrimaryAccount getPrimaryAccount() {
        //EFFECTS: return primaryAccount data member value that has been initialized at setPrimaryAccount() procedure/method
        return primaryAccount;
    }

    public void setPrimaryAccount(PrimaryAccount primaryAccount) {
        //REQUIRES: primaryAccount as PrimaryAccount object type
        //MODIFIES: this.primaryAccount
        this.primaryAccount = primaryAccount;
    }

    public SavingsAccount getSavingsAccount() {
        //EFFECTS: return savingsAccount data member value that has been initialized at setSavingsAccount() procedure/method
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        //REQUIRES: type as SavingsAccount object type
        //MODIFIES: this.savingsAccount
        this.savingsAccount = savingsAccount;
    }

    public void setEnabled(boolean enabled) {
        //REQUIRES: enabled as boolean type
        //MODIFIES: this.enabled
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        // EFFECTS: Return a self-descriptive string
        // Abstraction Function:
        // Displaying by Returning the result of user details
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", appointmentList=" + appointmentList +
                ", recipientList=" + recipientList +
                ", userRoles=" + userRoles +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        //EFFECTS: return true if it meet conidition AccountExpired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        //EFFECTS: return true if it meet conidition AccountNonLocked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        //EFFECTS: return true if it meet conidition CredentialsNonExpired
        return true;
    }

    @Override
    public boolean isEnabled() {
        //EFFECTS: return enabled data member value that has been initialized at setEnabled() procedure/method
        return enabled;
    }


}
