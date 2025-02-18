package com.example.wallet.models;

import java.math.BigDecimal;
import java.time.Instant;

public class Wallet{
    private int id;
    private Iban iban;
    private Currency currency;
    private BigDecimal balance;
    private Instant dateCreated;
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setIban(Iban iban) {
        this.iban = iban;
    }
    public Iban getIban() {
        return iban;
    }
}

