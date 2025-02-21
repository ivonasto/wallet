package com.example.wallet.dtos;

import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;

public record DepositRequest(
        @Schema(type = "string")
        Iban sender,
        @Schema(type = "string")
        Iban receiver,
        @Positive BigDecimal amount, Currency currency) {
}
