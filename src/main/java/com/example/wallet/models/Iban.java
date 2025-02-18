package com.example.wallet.models;

public class Iban {
    private String iban;
    private String country_code;


    public String getIban() { return iban;}

    public String getCountry_code() { return country_code; }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public Iban(String iban) {
        this.iban = iban;
        this.country_code = iban.substring(0,2);
    }

    public Iban() {
    }


}

