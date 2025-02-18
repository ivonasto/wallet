package com.example.wallet.services.iban;

import com.example.wallet.models.Iban;

public interface IbanService {

    Iban generateIban();

    boolean isValidIban(Iban iban);


}
