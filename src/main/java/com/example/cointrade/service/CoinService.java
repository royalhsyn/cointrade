package com.example.cointrade.service;

import com.example.cointrade.client.CoinClient;
import com.example.cointrade.client.CoinsClient;
import com.example.cointrade.entity.Coin;
import com.example.cointrade.repository.CoinRepo;
import com.example.cointrade.response.CoinResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoinService {

    private final CoinClient coinClient;

    private final CoinsClient coinsClient;

    private final CoinRepo coinRepo;

    public CoinService(CoinClient coinClient, CoinsClient coinsClient, CoinRepo coinRepo) {
        this.coinClient = coinClient;
        this.coinsClient = coinsClient;
        this.coinRepo = coinRepo;
    }


    public CoinResponse getCoin(String symbol){ return coinClient.getCoinPrice(symbol);}

    public List<CoinResponse> getCoins(){ return coinsClient.getCoins();}


    public void save(){
        coinRepo.deleteAll();
        List<CoinResponse> coins =coinsClient.getCoins();
        coins.stream()
                .map(coinResponse -> {
                    Coin coinEntity = new Coin();
                    coinEntity.setCoinName(coinResponse.getCoinName());
                    coinEntity.setPrice(coinResponse.getPrice());
                    coinEntity.setResponseLocalTime(LocalDateTime.now());
                    return coinEntity;
                })
                .forEach(coinRepo::save);
    }

}
