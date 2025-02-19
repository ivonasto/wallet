package com.example.wallet.models;

import jakarta.persistence.Embeddable;


@Embeddable
public class Iban {
    private String iban;

    public String getIban() { return iban;}

    public void setIban(String iban) {
        this.iban = iban.trim();
    }

    public Iban(String iban) {
        this.iban = iban.trim();
    }

    public Iban() {
    }


}

