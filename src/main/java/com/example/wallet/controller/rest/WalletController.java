package com.example.wallet.controller.rest;

import com.example.wallet.dtos.CreateWalletRequest;
import com.example.wallet.mappers.WalletMapper;
import com.example.wallet.models.Currency;
import com.example.wallet.models.Wallet;
import com.example.wallet.services.wallet.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/wallet")
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    public WalletController(WalletService walletService, WalletMapper walletMapper) {
        this.walletService = walletService;
        this.walletMapper = walletMapper;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable String id) {
        return "Your Balance is: 0.00";
    }

    @GetMapping
    public String getAll() {
        return "All your wallets are here:";
    }

    @PostMapping("/new")
    public void create(@Valid @RequestBody CreateWalletRequest createWalletRequest) {
        Wallet wallet = walletMapper.fromWalletRequest(createWalletRequest);
        walletService.create(wallet);
        
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Map<String, String>> handleInvalidArguments(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Malformed JSON");
        error.put("message", String.format("Unsupported currency type. Please try again with a valid currency from the following: %s", Arrays.toString(Currency.values())));
        error.put("status", "400");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }



    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id, @RequestBody String cvc) {
        return "Your Balance is: 0.00";
    }


}
