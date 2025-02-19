package com.example.wallet.controller.rest;

import com.example.wallet.dtos.CreateWalletRequest;
import com.example.wallet.exception.WalletNotFoundException;
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
import org.springframework.web.server.ResponseStatusException;


import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO send error when token expired
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
    public Wallet get(@PathVariable Long id) {
        try {
            return walletService.getById(id);
        } catch (WalletNotFoundException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(Principal principal) {
        List<Wallet> wallets = walletService.getAll(principal.getName());
        if (wallets.isEmpty()){
            return ResponseEntity.ok("You have no wallets");
        }
        return ResponseEntity.ok(wallets);

    }

    @PostMapping("/new")
    public void create(@Valid @RequestBody CreateWalletRequest createWalletRequest, Principal principal) {
        Wallet wallet = walletMapper.fromWalletRequest(createWalletRequest);
        walletService.create(wallet,principal.getName());

    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Map<String, String>> handleInvalidArguments(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, String> error = new HashMap<>();
        error.put("error", "Malformed JSON");
        error.put("message", String.format("Unsupported currency type. Please try again with a valid currency from the following: %s", Arrays.toString(Currency.values())));
        error.put("status", String.valueOf(status.value()));
        return ResponseEntity.status(status).body(error);
    }




}
