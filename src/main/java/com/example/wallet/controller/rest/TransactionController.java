package com.example.wallet.controller.rest;

import com.example.wallet.dtos.DepositRequest;
import com.example.wallet.dtos.WithdrawRequest;
import com.example.wallet.exception.InvalidTransactionException;
import com.example.wallet.exception.NotEnoughFundsException;
import com.example.wallet.exception.WalletNotFoundException;
import com.example.wallet.mappers.TransactionMapper;
import com.example.wallet.models.Transaction;
import com.example.wallet.services.transaction.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    TransactionService transactionService;
    TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @Operation(parameters = {@Parameter(name = "sender", description = "Account sending funds. Can be internal or external. If present in the app, should have enough funds."), @Parameter(name = "receiver", description = "Account receiving funds. Must be present in the app."), @Parameter(name = "amount", description = "Amount to be transferred. Must be positive."), @Parameter(name = "currency", description = "From set {EUR,BGN,USD}")})
    @PostMapping("/deposit")
    public void deposit(@Valid @RequestBody DepositRequest depositRequest) {
        Transaction transaction = transactionMapper.fromDepositRequest(depositRequest);
        try {
            // for simplicity validation of withdrawal from an account in our system was omitted
            transactionService.deposit(transaction);
        } catch (WalletNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (NotEnoughFundsException | InvalidTransactionException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @Operation(parameters = {@Parameter(name = "remitter", description = "Account to withdraw funds from. Must be present in the app and have enough funds."), @Parameter(name = "beneficiary", description = "Account receiving funds. Can be internal or external."), @Parameter(name = "amount", description = "Amount to be transferred from remitter to beneficiary. Must be positive."), @Parameter(name = "currency", description = "From set {EUR,BGN,USD}")})
    @PostMapping("/withdraw")
    public void withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        Transaction transaction = transactionMapper.fromWithdrawRequest(withdrawRequest);
        try {
            transactionService.withdraw(transaction);
        } catch (WalletNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (NotEnoughFundsException | InvalidTransactionException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Map<String, String>> handleInvalidArguments(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, String> error = new HashMap<>();
        error.put("error", "Malformed JSON");
        error.put("message", "Please provide valid IBANs, amount and currency");
        error.put("status", String.valueOf(status.value()));
        return ResponseEntity.status(status).body(error);
    }
}
