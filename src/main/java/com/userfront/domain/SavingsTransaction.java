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
public class SavingsTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;

    @ManyToOne
    @JoinColumn(name = "savings_account_id")
    private SavingsAccount savingsAccount;

    public SavingsTransaction() {}

    public SavingsTransaction(Date date, String description, String type, String status, double amount, BigDecimal availableBalance, SavingsAccount savingsAccount) {
        //REQUIRES: date as Date type, description as String type, type as String type, status as String type,
        // amount as double type, availableBalance as BigDecimal type, primaryAccount as SavingsAccount object
        //MODIFIES: this.date, this.description, this.type, this.status, this.amount, this.availableBalance, this.savingsAccount
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.savingsAccount = savingsAccount;
    }

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
        //REQUIRES: status as String type
        //MODIFIES: this.status
        this.status = status;
    }

    public double getAmount() {
        //EFFECTS: return amount data member value that has been initialized at setAmount) procedure/method
        return amount;
    }

    public void setAmount(double amount) {
        //REQUIRES: amount as double type
        //MODIFIES: this.amount
        this.amount = amount;
    }

    public BigDecimal getAvailableBalance() {
        //EFFECTS: return availableBalance data member value that has been initialized at setAvailableBalance() procedure/method
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        //REQUIRES: availableBalance as BigDecimal type
        //MODIFIES: this.availableBalance
        this.availableBalance = availableBalance;
    }

    public SavingsAccount getSavingsAccount() {
        //EFFECTS: return savingsAccount data member value that has been initialized at setSavingsAccount() procedure/method
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        //REQUIRES: savingsAccount as SavingsAccount object type
        //MODIFIES: this.savingsAccount
        this.savingsAccount = savingsAccount;
    }
}
