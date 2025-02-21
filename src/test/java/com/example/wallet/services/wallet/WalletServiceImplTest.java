package com.example.wallet.services.wallet;

import com.example.wallet.exception.NotEnoughFundsException;
import com.example.wallet.models.Currency;
import com.example.wallet.models.Iban;
import com.example.wallet.models.User;
import com.example.wallet.models.Wallet;
import com.example.wallet.persistence.wallet.WalletRepository;
import com.example.wallet.services.currency.CurrencyService;
import com.example.wallet.services.iban.IbanService;
import com.example.wallet.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.example.wallet.helpers.Helpers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    @Mock
    private WalletRepository walletRepository;
    @Mock
    private IbanService ibanService;
    @Mock
    private UserService userDetailsService;
    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private WalletServiceImpl walletService;

    @Test
    void create_Should_CreateNewIban_WhenItExists() {
        Wallet wallet = createMockWallet();
        User user = createMockUser();
        String username = createMockUser().getUsername();
        Iban iban1 = createMockIban();
        Iban iban2 = createMockIban2();

        when(ibanService.generateIban())
                .thenReturn(createMockIban())
                .thenReturn(createMockIban2());

        when(userDetailsService.findByUsername("Thors")).thenReturn(user);

        when(walletRepository.get(iban1.getIban())).thenReturn(Optional.of(createMockWallet()));
        when(walletRepository.get(iban2.getIban())).thenReturn(Optional.empty());

        walletService.create(wallet, username);

        Mockito.verify(walletRepository, times(1)).save(wallet);
        Mockito.verify(ibanService, times(2)).generateIban();

    }

    @Test
    void getById_Should_ReturnWallet_WhenItExists() {
        Wallet wallet = createMockWallet();
        Mockito.when(walletRepository.findById(1L))
                .thenReturn(Optional.of(wallet));

        Wallet result = walletService.getById(1L);

        Assertions.assertEquals(wallet, result);

    }

    @Test
    void get_Should_ReturnWallet_WhenItExists() {
        Wallet wallet = createMockWallet();
        Mockito.when(walletRepository.get(wallet.getIban().getIban()))
                .thenReturn(Optional.of(wallet));

        Wallet result = walletService.get(wallet.getIban());

        Assertions.assertEquals(wallet, result);
    }

    @Test
    void deposit_Should_IncreaseBalance() {
        Wallet wallet = createMockWallet();
        wallet.setBalance(BigDecimal.valueOf(100.00));

        BigDecimal depositAmount = BigDecimal.valueOf(50.00);
        Currency depositCurrency = Currency.EUR;
        BigDecimal convertedAmount = BigDecimal.valueOf(100.01);

        when(currencyService.convert(depositAmount, depositCurrency, wallet.getCurrency()))
                .thenReturn(convertedAmount);

        walletService.deposit(wallet, depositAmount, depositCurrency);

        Assertions.assertEquals(BigDecimal.valueOf(200.01), wallet.getBalance());
        Mockito.verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    void withdraw_Should_Throw_WhenTooFewFunds() {

        Wallet wallet = createMockWallet();
        wallet.setBalance(BigDecimal.valueOf(100.00));

        BigDecimal depositAmount = BigDecimal.valueOf(50.00);
        Currency depositCurrency = Currency.EUR;
        BigDecimal convertedAmount = BigDecimal.valueOf(100.01);

        when(currencyService.convert(depositAmount, depositCurrency, wallet.getCurrency()))
                .thenReturn(convertedAmount);

        Assertions.assertThrows(NotEnoughFundsException.class, () -> walletService.withdraw(wallet, depositAmount, depositCurrency));
    }

    @Test
    void deposit_Should_DecreaseBalance() {
        Wallet wallet = createMockWallet();
        wallet.setBalance(BigDecimal.valueOf(100.00));

        BigDecimal depositAmount = BigDecimal.valueOf(49.99);
        Currency depositCurrency = Currency.BGN;

        when(currencyService.convert(depositAmount, depositCurrency, wallet.getCurrency()))
                .thenReturn(depositAmount);

        walletService.withdraw(wallet, depositAmount, depositCurrency);

        Assertions.assertEquals(BigDecimal.valueOf(50.01), wallet.getBalance());
        Mockito.verify(walletRepository, times(1)).save(wallet);
    }

    @Test
    void getAll_Should_ReturnAllWallets() {

        Wallet wallet = createMockWallet();
        Wallet wallet2 = createMockWallet();
        User user = createMockUser();

        List<Wallet> wallets = List.of(wallet, wallet2);

        when(walletRepository.findAll(user.getUsername())).thenReturn(wallets);

        List<Wallet> result = walletService.getAll(user.getUsername());

        Assertions.assertEquals(2, result.size());
        Mockito.verify(walletRepository, times(1)).findAll(user.getUsername());
    }
}