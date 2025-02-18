package com.example.wallet.services.wallet;

import com.example.wallet.exception.NotEnoughFundsException;
import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import com.example.wallet.models.Wallet;
import com.example.wallet.services.currency.CurrencyService;

import com.example.wallet.services.iban.IbanService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@PropertySource("classpath:messages.properties")
public class WalletServiceImpl implements WalletService {

    CurrencyService currencyService;
    IbanService ibanService;


    @Override
    public void create(Wallet wallet) {
        wallet.setIban(ibanService.generateIban());
    }

    @Override
    public Wallet get(Iban iban) {
        return null;
    }

    @Override
    public void deposit(Wallet wallet, BigDecimal amount, Currency currency) {

    }

    @Override
    public void withdraw(Wallet wallet, BigDecimal amount, Currency currency) {

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughFundsException(wallet);
        }
    }

}
