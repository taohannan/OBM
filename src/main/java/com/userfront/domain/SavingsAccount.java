package com.userfront.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SavingsAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SavingsTransaction> savingsTransactionList;

    public Long getId() {
        //EFFECTS: return id data member value that has been initialized at setId() procedure/method
        return id;
    }

    public void setId(Long id) {
        //REQUIRES: id as Long type
        //MODIFIES: this.id
        this.id = id;
    }

    public int getAccountNumber() {
        //EFFECTS: return accountNumber data member value that has been initialized at setAcountNumber() procedure/method
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        //REQUIRES: accountNumber as integer type
        //MODIFIES: this.accountNumber
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        //EFFECTS: return accountBalance data member value that has been initialized at setAccountBalance() procedure/method
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        //REQUIRES: accountBalance as BigDecimal type
        //MODIFIES: this.accountBalance
        this.accountBalance = accountBalance;
    }

    public List<SavingsTransaction> getSavingsTransactionList() {
        //EFFECTS: return savingsTransactionList data member value that has been initialized at
        // setSavingsTransactionList() procedure/method
        return savingsTransactionList;
    }

    public void setSavingsTransactionList(List<SavingsTransaction> savingsTransactionList) {
        //REQUIRES: savingsTransactionList as List type
        //MODIFIES: this.savingsTransactionList
        this.savingsTransactionList = savingsTransactionList;
    }


}
