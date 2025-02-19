package com.example.wallet.persistence.wallet;

import com.example.wallet.models.Iban;
import com.example.wallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM wallets where iban = ?")
    Optional<Wallet> get(Iban iban);

}
