package com.example.wallet.services.currency;

import com.example.wallet.models.Currency;

import java.math.BigDecimal;

public interface CurrencyService {


    BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency);

    BigDecimal convert(BigDecimal amount, Currency fromCurrency, Currency toCurrency);
}
