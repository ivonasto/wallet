package com.example.wallet.models;

public enum Currency {

    BGN("BGN"),
    USD("USD"),
    EUR("EUR");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
