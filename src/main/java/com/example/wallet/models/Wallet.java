package com.example.wallet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Entity
@Table(name = "wallets")
public class Wallet{
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private int id;

   @JsonUnwrapped
    private Iban iban;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal balance;

    @JsonIgnore
    @Column(name = "date_of_creation")
    private Instant dateCreated;

    @ManyToOne
    @JoinColumn(name = "owner")
    @JsonIgnore
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
       return balance.setScale(2, RoundingMode.HALF_UP);
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

