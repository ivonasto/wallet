package com.example.wallet.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @AttributeOverride(name = "iban", column = @Column(name = "sender"))
    private Iban sender;

    @AttributeOverride(name = "iban", column = @Column(name = "recipient"))
    private Iban recipient;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(name = "date_of_creation")
    private Instant createdAt;

    public Transaction() {
    }

    public Transaction(Iban sender, Iban recipient, BigDecimal amount, Currency currency, Instant createdAt) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
    }

    public Iban getSender() {
        return sender;
    }

    public void setSender(Iban sender) {
        this.sender = sender;
    }

    public Iban getRecipient() {
        return recipient;
    }

    public void setRecipient(Iban recipient) {
        this.recipient = recipient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
