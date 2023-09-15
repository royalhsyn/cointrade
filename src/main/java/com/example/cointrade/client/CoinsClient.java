package com.example.cointrade.client;

import com.example.cointrade.response.CoinResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "coins",url = "${coins.prices.url}")
public interface CoinsClient {

    @GetMapping
    List<CoinResponse> getCoins();
}
