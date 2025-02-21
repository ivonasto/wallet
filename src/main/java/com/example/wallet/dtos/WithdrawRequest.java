package com.example.wallet.dtos;

import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record WithdrawRequest(
        @Schema(type = "string")
        Iban remitter,
        @Schema(type = "string")
        Iban beneficiary,
        @Positive BigDecimal amount,
        Currency currency) {
}
