package com.userfront.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PrimaryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;

    public PrimaryTransaction() {}


    public PrimaryTransaction(Date date, String description, String type, String status, double amount, BigDecimal availableBalance, PrimaryAccount primaryAccount) {
        //REQUIRES: date as Date type, description as String type, type as String type, status as String type,
        // amount as double type, availableBalance as BigDecimal type, primaryAccount as PrimaryAccount object
        //MODIFIES: this.date, this.description, this.type, this.status, this.amount, this.availableBalance, this.primaryAccount
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.primaryAccount = primaryAccount;
    }

    @ManyToOne
    @JoinColumn(name = "primary_account_id")
    private PrimaryAccount primaryAccount;

    public Long getId() {
        //EFFECTS: return id data member value that has been initialized at setId() procedure/method
        return id;
    }

    public void setId(Long id) {
        //REQUIRES: id as Long type
        //MODIFIES: this.id
        this.id = id;
    }

    public Date getDate() {
        //EFFECTS: return date data member value that has been initialized at setDate() procedure/method
        return date;
    }

    public void setDate(Date date) {
        //REQUIRES: date as Date type
        //MODIFIES: this.date
        this.date = date;
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

    public String getType() {
        //EFFECTS: return type data member value that has been initialized at setType() procedure/method
        return type;
    }

    public void setType(String type) {
        //REQUIRES: type as String type
        //MODIFIES: this.type
        this.type = type;
    }

    public String getStatus() {
        //EFFECTS: return status data member value that has been initialized at setStatus() procedure/method
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public PrimaryAccount getPrimaryAccount() {
        return primaryAccount;
    }

    public void setPrimaryAccount(PrimaryAccount primaryAccount) {
        this.primaryAccount = primaryAccount;
    }

}
