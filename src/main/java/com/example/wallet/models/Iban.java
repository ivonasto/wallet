package com.example.wallet.models;

public class Iban {
    private String iban;

    public String getIban() { return iban;}
    public String getCountryCode() { return iban.substring(0,2);}

    public void setIban(String iban) {
        this.iban = iban.trim();
    }

    public Iban(String iban) {
        this.iban = iban.trim();
    }

    public Iban() {
    }


}

