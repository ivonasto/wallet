package com.example.wallet.services.iban;

import com.example.wallet.exception.InvalidIbanException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.wallet.helpers.Helpers.*;

@ExtendWith(MockitoExtension.class)
public class IbanServiceImplTest {

    @InjectMocks
    IbanServiceImpl ibanService;


    @Test
    public void verifyIban_ShouldThrow_When_ContainsInvalidCharacters() {
        Assertions.assertThrows(InvalidIbanException.class, () ->
            ibanService.verifyIban(createInvalidMockIban()));
    }

    @Test
    public void isValidIban_ShouldThrow_When_IbanIsTooShort() {
        Assertions.assertThrows(InvalidIbanException.class, () ->
            ibanService.verifyIban(createInvalidMockIban2()));
    }

    @Test
    public void isValidIban_ShouldNotThrow_When_VerifyIban() {
        Assertions.assertDoesNotThrow(() ->
                ibanService.verifyIban(createValidMockIban()));
    }

    @Test
    public void generateIban_Should_Generate_ValidIban() {
        Assertions.assertDoesNotThrow(() ->
                ibanService.verifyIban(ibanService.generateIban()));
    }

}