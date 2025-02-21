package com.example.wallet.dtos;


import com.example.wallet.models.Currency;
import jakarta.validation.constraints.NotNull;


public record CreateWalletRequest(@NotNull(message = "Currency is required") Currency currency) {
}
