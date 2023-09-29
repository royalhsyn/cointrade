package com.example.cointrade.repository;

import com.example.cointrade.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepo extends JpaRepository<Coin,Long> {
}
