package com.example.wallet.services.iban;

import com.example.wallet.models.Iban;

public interface IbanService {

    Iban generateIban();

    void verifyIban(Iban iban);

    // otherwise isValidIban would have side effects
    Iban removeWhitespace(Iban iban);

}
