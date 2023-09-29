package com.example.cointrade.repository;

import com.example.cointrade.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet,Long> {
}
