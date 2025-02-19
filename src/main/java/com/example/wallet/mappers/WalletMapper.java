package com.example.wallet.mappers;

import com.example.wallet.dtos.CreateWalletRequest;
import com.example.wallet.models.Wallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Component
public class WalletMapper {

   public Wallet fromWalletRequest(CreateWalletRequest createWalletRequest){
      Wallet wallet = new Wallet();
      wallet.setCurrency(createWalletRequest.currency());
      wallet.setDateCreated(Instant.now());
      wallet.setBalance(BigDecimal.ZERO);
      return wallet;
   }
}
