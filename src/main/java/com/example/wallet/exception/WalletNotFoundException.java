package com.example.wallet.exception;

import com.example.wallet.models.Iban;
import com.example.wallet.models.Wallet;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(String message) {
        super(message);
    }

    public WalletNotFoundException(Wallet wallet) {
        super("Wallet with IBAN:" + wallet.getIban().getIban() + " not found in our system");
    }
    public WalletNotFoundException(Iban iban) {
        super("Wallet with IBAN: " + iban.getIban() + " not found in our system");
    }


}
