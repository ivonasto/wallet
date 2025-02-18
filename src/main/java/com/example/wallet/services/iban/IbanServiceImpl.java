package com.example.wallet.services.iban;

import org.springframework.stereotype.Service;

@Service
public class IbanServiceImpl implements IbanService {




    public String getCheckSumReadyIban(String iban) {

        iban = cleanIban(iban);





    }

    private static String cleanIban(String iban) {
        return iban.replace("-", "");
    }

    // is valid iban contains check sum and characters so conversion from letter to num will serve to
    // maintain only chars are present

    private boolean validateIban(String iban) {

    }
}
