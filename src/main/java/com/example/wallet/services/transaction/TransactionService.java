package com.example.wallet.services.transaction;

import com.example.wallet.models.Transaction;


public interface TransactionService {
    void deposit(Transaction transaction);
    void withdraw(Transaction transaction);
}
