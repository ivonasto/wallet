package com.example.wallet.services.wallet;

import com.example.wallet.models.Wallet;
import com.example.wallet.services.currency.CurrencyService;

import com.example.wallet.services.iban.IbanService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:messages.properties")
public class WalletServiceImpl implements WalletService {

    CurrencyService currencyService;
    IbanService ibanService;


    @Override
    public void create(Wallet wallet) {
        wallet.setIban(ibanService.generateIban());


    }

}
