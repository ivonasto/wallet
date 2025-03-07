package com.example.wallet.services.wallet;

import com.example.wallet.exception.NotEnoughFundsException;
import com.example.wallet.exception.WalletNotFoundException;
import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import com.example.wallet.models.User;
import com.example.wallet.models.Wallet;
import com.example.wallet.persistence.wallet.WalletRepository;
import com.example.wallet.services.currency.CurrencyService;

import com.example.wallet.services.iban.IbanService;
import com.example.wallet.services.user.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    CurrencyService currencyService;
    IbanService ibanService;
    UserService userDetailsService;
    WalletRepository walletRepository;

    public WalletServiceImpl(CurrencyService currencyService, IbanService ibanService, UserService userDetailsService, WalletRepository walletRepository) {
        this.currencyService = currencyService;
        this.ibanService = ibanService;
        this.userDetailsService = userDetailsService;
        this.walletRepository = walletRepository;
    }

    @Override
    public void create(Wallet wallet, String username) {
        User owner = userDetailsService.findByUsername(username);
        Iban iban = ibanService.generateIban();

        while (walletRepository.get(iban.getIban()).isPresent()) {
            iban = ibanService.generateIban();
        }

        wallet.setIban(iban);
        wallet.setOwner(owner);
        walletRepository.save(wallet);
    }

    @Override
    public Wallet getById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (wallet.isPresent()) {
            return wallet.get();
        } else {
            throw new WalletNotFoundException(String.format("Wallet with id %s not found", id));
        }
    }

    @Override
    public Wallet get(Iban iban) {
        Optional<Wallet> wallet = walletRepository.get(iban.getIban());
        if (wallet.isPresent()) {
            return wallet.get();
        }
        throw new WalletNotFoundException(iban);
    }

    @Override
    public void deposit(Wallet wallet, BigDecimal amount, Currency currency) {
        BigDecimal amountInWalletCurrency = currencyService.convert(amount, currency, wallet.getCurrency());
        wallet.setBalance(wallet.getBalance().add(amountInWalletCurrency));
        walletRepository.save(wallet);
    }

    @Override
    public void withdraw(Wallet wallet, BigDecimal amount, Currency currency) {
        BigDecimal amountInWalletCurrency = currencyService.convert(amount, currency, wallet.getCurrency());

        if (wallet.getBalance().compareTo(amountInWalletCurrency) < 0) {
            throw new NotEnoughFundsException(wallet);
        }
        wallet.setBalance(wallet.getBalance().subtract(amountInWalletCurrency));
        walletRepository.save(wallet);
    }

    @Override
    public List<Wallet> getAll(String username) {
        return walletRepository.findAll(username);
    }

}
