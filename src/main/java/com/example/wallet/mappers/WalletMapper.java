package com.example.wallet.mappers;

import com.example.wallet.dtos.CreateWalletRequest;
import com.example.wallet.models.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

   public Wallet fromWalletRequest(CreateWalletRequest createWalletRequest){
      Wallet wallet = new Wallet();
      wallet.setCurrency(createWalletRequest.currency());
      return wallet;
   }
}
