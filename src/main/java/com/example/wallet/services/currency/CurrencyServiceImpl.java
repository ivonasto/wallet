package com.example.wallet.services.currency;

import com.example.wallet.models.Currency;
import com.example.wallet.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final Map<Pair<Currency, Currency>, BigDecimal> exchangeRates = new HashMap<>();

    CurrencyServiceImpl() {
        exchangeRates.put(new Pair<>(Currency.EUR, Currency.BGN), BigDecimal.valueOf(1.95583));
        exchangeRates.put(new Pair<>(Currency.BGN, Currency.EUR), BigDecimal.valueOf(0.51129188));
        exchangeRates.put(new Pair<>(Currency.USD, Currency.EUR), BigDecimal.valueOf(0.95730551));
        exchangeRates.put(new Pair<>(Currency.EUR, Currency.USD), BigDecimal.valueOf(1.04460));
        exchangeRates.put(new Pair<>(Currency.BGN, Currency.USD), BigDecimal.valueOf(0.534069));
        exchangeRates.put(new Pair<>(Currency.USD, Currency.BGN), BigDecimal.valueOf(1.8724161));
        exchangeRates.put(new Pair<>(Currency.USD, Currency.USD), BigDecimal.valueOf(1));
        exchangeRates.put(new Pair<>(Currency.BGN, Currency.BGN), BigDecimal.valueOf(1));
        exchangeRates.put(new Pair<>(Currency.EUR, Currency.EUR), BigDecimal.valueOf(1));
    }

    @Override
    public BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        return exchangeRates.get(new Pair<>(fromCurrency, toCurrency));
    }

    @Override
    public BigDecimal convert(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        return getExchangeRate(fromCurrency, toCurrency).multiply(amount);
    }
}
