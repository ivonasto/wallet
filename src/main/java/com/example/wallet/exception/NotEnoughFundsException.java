package com.example.wallet.exception;

import com.example.wallet.models.Currency;
import com.example.wallet.models.Wallet;

import java.math.BigDecimal;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException(String message) {
        super(message);
    }

    public NotEnoughFundsException(Wallet wallet) {
    super(String.format("Wallet with Iban %s does not have enough funds.", wallet.getIban()));
    }


}
