package com.example.wallet.helpers;

import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import com.example.wallet.models.User;
import com.example.wallet.models.Wallet;

import java.math.BigDecimal;
import java.util.List;

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

    public static User createMockUser() {
        return new User(1, "Thors", "password", List.of());
    }

    public static Iban createMockIban() {
        return new Iban("NL73ABNA4883846911");
    }

    public static Iban createMockIban2() {
        return new Iban("IS668948828848915661532367");
    }

    public static Wallet createMockWallet() {
        Wallet wallet = new Wallet();
        wallet.setId(1);
        wallet.setIban(createMockIban());
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setOwner(createMockUser());
        wallet.setCurrency(Currency.BGN);
        return wallet;
    }


}
