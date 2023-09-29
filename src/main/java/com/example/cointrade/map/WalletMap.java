package com.example.cointrade.map;

import com.example.cointrade.dto.WalletDto;
import com.example.cointrade.entity.Wallet;
import org.mapstruct.Mapper;

@Mapper
public interface WalletMap {

    WalletDto toDto(Wallet wallet);

    Wallet toEntity(WalletDto walletDto);
}
