package com.example.cointrade.service;

import com.example.cointrade.repository.WalletRepo;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepo walletRepo;

    public WalletService(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }
}
