package com.example.wallet.services.wallet;

import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import com.example.wallet.models.Wallet;

import java.math.BigDecimal;

public interface WalletService {
    void create(Wallet wallet);
    Wallet get(Iban iban);
    //  currency same ? if not convert
    void deposit(Wallet wallet, BigDecimal amount, Currency currency);
    // throws if not enough funds
    void withdraw(Wallet wallet, BigDecimal amount, Currency currency);
}
