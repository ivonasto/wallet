package com.example.wallet.models;

import jakarta.persistence.Embeddable;

import java.util.Objects;


@Embeddable
public class Iban {
    private String iban;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban.trim();
    }

    public Iban(String iban) {
        this.iban = iban.trim();
    }

    public Iban() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Iban iban1 = (Iban) o;
        return Objects.equals(iban, iban1.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(iban);
    }
}

