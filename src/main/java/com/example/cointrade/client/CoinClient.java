package com.example.cointrade.client;

import com.example.cointrade.response.CoinResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "coin", url = "${coin.url}")
public interface CoinClient {

    @GetMapping
    CoinResponse getCoinPrice(@RequestParam("symbol") String symbol);
}
