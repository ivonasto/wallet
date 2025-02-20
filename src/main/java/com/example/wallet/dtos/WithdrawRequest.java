package com.example.wallet.dtos;

import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record WithdrawRequest(Iban remitter, Iban beneficiary, @Positive BigDecimal amount, Currency currency) {
}
