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
public class PrimaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "primaryAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PrimaryTransaction> primaryTransactionList;

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
        //EFFECTS: return accountNumber data member value that has been initialized at setAccountNumber() procedure/method
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        //REQUIRES: accountNumber as int type
        //MODIFIES: this.accountNumber
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        //EFFECTS: return accountBalance data member value that has been initialized at setAccountBalance() procedure/method
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        //REQUIRES: accountBalance as BigDecimal  type
        //MODIFIES: this.accountBalance
        this.accountBalance = accountBalance;
    }

    public List<PrimaryTransaction> getPrimaryTransactionList() {
        //EFFECTS: return primaryTransactionList List value that has been initialized at setPrimaryTransactionList() procedure/method
        return primaryTransactionList;
    }

    public void setPrimaryTransactionList(List<PrimaryTransaction> primaryTransactionList) {
        //REQUIRES: primaryTransactionList as List type
        //MODIFIES: this.primaryTransactionList
        this.primaryTransactionList = primaryTransactionList;
    }


}



