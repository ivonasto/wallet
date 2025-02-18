package com.example.wallet.dtos;

import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;
// TODO check if @Positive works
public record DepositRequest(Iban sender, Iban receiver, @Positive @NotEmpty BigDecimal amount, Currency currency ) {}
