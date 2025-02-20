package com.example.wallet.services.iban;

import com.example.wallet.models.Iban;
import org.springframework.stereotype.Service;
import com.example.wallet.exception.InvalidIbanException;

import java.math.BigInteger;
import java.util.UUID;

// TODO fix error messages -> not hardcoded
@Service
public class IbanServiceImpl implements IbanService {

    @Override
    public Iban generateIban() {
        // Generate IBAN with checksum equal to 00
        String iban = generateIbanWithoutCheckSum();
        // Move country code and checksum to the back (first 4 characters become last)
        String rearrangedIban = rearrangeIban(iban);
        // Convert to BigInteger mapping letters to digits (ASCII)
        BigInteger ibanToNumber = convertIbanToBigInteger(rearrangedIban);
        // Calculate checksum based on algorithm 97-10
        BigInteger checkSum = BigInteger.valueOf(98L).subtract(ibanToNumber.mod(BigInteger.valueOf(97)));
        // Insert checksum into the IBAN
        iban = iban.replaceFirst("00", String.format("%02d",checkSum));

        return new Iban(iban);
    }

    /**
     * The method checks if an IBAN passes the Mod 97-10 test (<a href= https://en.wikipedia.org/wiki/ISO/IEC_7064></a>ISO 7064) and if all symbols are valid:
     *
     * <ul>
     *   <li>Alphabetic characters (both uppercase and lowercase letters from A-Z)</li>
     *   <li>Digits (0-9)</li>
     * </ul>
     * <p>
     * It ensures the validity of the IBAN's structure and the check digits (2 digits after country code).
     * It does not guarantee the existence of the referenced bank or account.
     *
     * @param iban - the IBAN of the account free of whitespace
     * @throws InvalidIbanException if IBAN :
     *                              <ul>
     *                                  <li> does not pass the mod 97 check</li>
     *                                  <li> contains inappropriate symbols </li>
     *                                  <li> is too short ( less than 15 symbols) </li>
     *                                  <li> if is empty, null </li>
     *                              </ul>
     **/

    @Override
    public void verifyIban(Iban iban) {
        if (iban == null || iban.getIban().isEmpty()) {
            throw new InvalidIbanException("IBAN cannot be null or empty.");
        }
        if (iban.getIban().length() < 15) {
            throw new InvalidIbanException("Iban must be at least 15 characters.");
        }

        // verify that the IBAN .. to the ISO 7064 standard
        verifyContainsValidSymbols(iban);
        String rearrangedIban = rearrangeIban(iban);
        BigInteger result = convertIbanToBigInteger(rearrangedIban);
        BigInteger modCheck = result.mod(BigInteger.valueOf(97L));

        if (!(modCheck.equals(BigInteger.ONE))) {
            throw new InvalidIbanException("Invalid IBAN.");
        }
    }
    private String generateIbanWithoutCheckSum() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return "WT00" + uuid.substring(0, 16).toUpperCase();

    }

    private BigInteger convertIbanToBigInteger(String iban) {
        StringBuilder numericIban = new StringBuilder();

        for (char c : iban.toCharArray()) {
            if (Character.isDigit(c)) {
                numericIban.append(c);
            } else if (Character.isLetter(c)) {
                int letterValue = Character.toUpperCase(c) - 'A' + 10;
                numericIban.append(letterValue);
            } else {
                throw new IllegalArgumentException("Invalid character in IBAN: " + c);
            }
        }
        return new BigInteger(numericIban.toString());
    }

    private String rearrangeIban(Iban iban) {

        return iban.getIban().substring(4) +
                iban.getIban().substring(0, 4);

    }

    private String rearrangeIban(String iban) {

        return iban.substring(4) +
                iban.substring(0, 4);

    }

    private void verifyContainsValidSymbols(Iban iban) {
        for (char c : iban.getIban().toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                throw new InvalidIbanException("Invalid IBAN symbol: " + c);
            }
        }
    }

}
