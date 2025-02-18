package com.example.wallet.services.transaction;

import com.example.wallet.exception.WalletNotFoundException;
import com.example.wallet.models.Transaction;
import com.example.wallet.models.Wallet;
import com.example.wallet.services.currency.CurrencyService;
import com.example.wallet.services.iban.IbanService;
import com.example.wallet.services.wallet.WalletService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    IbanService ibanService;
    CurrencyService currencyService;
    WalletService walletService;

    public TransactionServiceImpl(CurrencyService currencyService, IbanService ibanService,WalletService walletService) {
        this.currencyService = currencyService;
        this.ibanService = ibanService;
        this.walletService = walletService;
    }

    @Override
    public void deposit(Transaction transaction) {
        // existing receiver?
        // repo shall throw err if not exist
        Wallet receivingWallet = walletService.get(transaction.getRecipient());
        try {
        Wallet senderWallet = walletService.get(transaction.getSender());
        // throws error if no funds
        walletService.withdraw(senderWallet, transaction.getAmount(), transaction.getCurrency());}
        catch (WalletNotFoundException ex) {
            // assume external bank has funds to cover transaction
            // assume user in our app has given permission to withdraw from their account
            // TODO pending transaction endpoint
            // only verify it is a valid iban
            ibanService.verifyIban(transaction.getSender());
        }
        // catch if throw and assume the sender has funds
        // check IBAN validity of sender

        // existing receiver ? call withdraw , if it fails -> error no moneyz else decrease balance of wallet
        // increase balance
        walletService.deposit(receivingWallet, transaction.getAmount(), transaction.getCurrency());
    }

    @Override
    public void withdraw(Transaction transaction) {
        // existing sender? if not error else check amount , remove converted currency amount
        // existing receiver ? call deposit
        // check IBAN validity of receiver


    }
}
