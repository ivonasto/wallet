package com.example.wallet.services.wallet;

import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import com.example.wallet.models.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
    void create(Wallet wallet, String username);
    Wallet get(Iban iban);
    //  currency same ? if not convert
    void deposit(Wallet wallet, BigDecimal amount, Currency currency);
    // throws if not enough funds
    void withdraw(Wallet wallet, BigDecimal amount, Currency currency);
    List<Wallet> getAll(String username);
    Wallet getById(Long id);
}
