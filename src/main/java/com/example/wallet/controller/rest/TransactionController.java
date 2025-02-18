package com.example.wallet.controller.rest;

import com.example.wallet.dtos.DepositRequest;
import com.example.wallet.dtos.WithdrawRequest;
import com.example.wallet.exception.WalletNotFoundException;
import com.example.wallet.mappers.TransactionMapper;
import com.example.wallet.models.Transaction;
import com.example.wallet.services.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {

    TransactionService transactionService;
    TransactionMapper transactionMapper;


    @PostMapping("/deposit")
    public void deposit(@Valid @RequestBody DepositRequest depositRequest) {
        Transaction transaction = transactionMapper.fromDepositRequest(depositRequest);
        try {
            // for simplicity validation of withdrawal from an account in our system was omitted

            transactionService.deposit(transaction);
        } catch (WalletNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }


    }

    @PostMapping("/withdraw")
    public void withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        Transaction transaction = transactionMapper.fromWithdrawRequest(withdrawRequest);
        // check if remitter is the same as user , else authorization exception

        try {
            transactionService.withdraw(transaction);
        } catch (WalletNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }


    }
}
