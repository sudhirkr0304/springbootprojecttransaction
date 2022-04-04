package com.sudhir.javaproject.model;

import jdk.jfr.Enabled;

import javax.persistence.Entity;
import javax.persistence.Id;

public class TransactionObject {

    private String accountNumber;
    private String type;
    private String amount;
    private String currency;
    private String accountFrom;

    public TransactionObject( String accountNumber, String type, String amount, String currency, String accountFrom) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.accountFrom = accountFrom;
    }

    public TransactionObject() {
    }



    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    @Override
    public String toString() {
        return this.accountNumber+ " " + this.amount+ " " + this.type + " " + this.currency+ " " + this.accountFrom;
    }
}
