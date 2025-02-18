package com.example.wallet.services.currency;

import com.example.wallet.models.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    public static boolean approximatelyEqual(BigDecimal a, BigDecimal b, double eps) {
        // math.abs(a - b) < eps
        //alternatively (a - b) < eps || (a-b) > eps
        return a.subtract(b).abs().compareTo(BigDecimal.valueOf(eps)) < 0;
    }

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Test
    void convert_USD_to_BGN_AndBackIsSame() {
        BigDecimal result = currencyService.convert(BigDecimal.TEN, Currency.USD, Currency.BGN);
        BigDecimal actual = currencyService.convert(result, Currency.BGN, Currency.USD);
        Assertions.assertTrue(approximatelyEqual(BigDecimal.TEN, actual, 0.001));

    }

    @Test
    void convert_EUR_to_BGN_AndBackIsSame() {
        BigDecimal result = currencyService.convert(BigDecimal.TEN, Currency.EUR, Currency.BGN);
        BigDecimal actual = currencyService.convert(result, Currency.BGN, Currency.EUR);
        Assertions.assertTrue(approximatelyEqual(BigDecimal.TEN, actual, 0.001));

    }

    @Test
    void convert_USD_to_EUR_AndBackIsSame() {
        BigDecimal result = currencyService.convert(BigDecimal.TEN, Currency.USD, Currency.EUR);
        BigDecimal actual = currencyService.convert(result, Currency.EUR, Currency.USD);
        Assertions.assertTrue(approximatelyEqual(BigDecimal.TEN, actual, 0.001));
    }

}