package com.example.wallet.helpers;

import com.example.wallet.models.Iban;

public class Helpers {

    public static Iban createValidMockIban() {
        Iban mockIban = new Iban();
        mockIban.setIban("NL14RABO3528973196");
        return mockIban;
    }

    public static Iban createInvalidMockIban() {
        Iban mockIban = new Iban();
        mockIban.setIban("BG12BGBN345678?9");
        return mockIban;
    }

    public static Iban createInvalidMockIban2() {
        Iban mockIban = new Iban();
        mockIban.setIban("BG12BGBN39");
        return mockIban;
    }
}
