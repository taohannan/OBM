package com.userfront.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String accountNumber;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Long getId() {
        //EFFECTS: return id data member value that has been initialized at setId() procedure/method
        return id;
    }

    public void setId(Long id) {
        //REQUIRES: id as Long type
        //MODIFIES: this.id
        this.id = id;
    }

    public String getName() {
        //EFFECTS: return name; data member value that has been initialized at setName;() procedure/method
        return name;
    }

    public void setName(String name) {
        //REQUIRES: name as String type
        //MODIFIES: this.name
        this.name = name;
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

    public String getAccountNumber() {
        //EFFECTS: return accountNumber data member value that has been initialized at setAccountNumber() procedure/method
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        //REQUIRES: accountNumber as String type
        //MODIFIES: this.accountNumber
        this.accountNumber = accountNumber;
    }

    public User getUser() {
        //EFFECTS: return user data member value that has been initialized at setUser() procedure/method
        return user;
    }

    public void setUser(User user) {
        //REQUIRES: user as User object
        //MODIFIES: this.user
        this.user = user;
    }

    public String getDescription() {
        //EFFECTS: return description data member value that has been initialized at setDescription() procedure/method
        return description;
    }

    public void setDescription(String description) {
        //REQUIRES: description as String type
        //MODIFIES: this.description
        this.description = description;
    }
}

