package com.example.wallet.mappers;

import com.example.wallet.dtos.DepositRequest;
import com.example.wallet.dtos.WithdrawRequest;
import com.example.wallet.models.Transaction;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TransactionMapper {

    public Transaction fromDepositRequest(DepositRequest depositRequest) {
        return new Transaction(depositRequest.sender(), depositRequest.receiver(), depositRequest.amount(), depositRequest.currency(), Instant.now());
    }

    public Transaction fromWithdrawRequest(WithdrawRequest withdrawRequest) {
        return new Transaction(withdrawRequest.remitter(), withdrawRequest.beneficiary(), withdrawRequest.amount(), withdrawRequest.currency(), Instant.now());
    }
}
