package com.example.wallet.dtos;

import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;

public record DepositRequest(Iban sender, Iban receiver, @Positive BigDecimal amount, Currency currency ) {}
