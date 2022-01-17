package com.example.javatesttask.service;

import com.example.javatesttask.domain.Currency;
import com.example.javatesttask.domain.CurrencyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class CurrencyClient {
//    static final String URL_BTC_USD = "https://cex.io/api/last_price/BTC/USD";
//    static final String URL_ETH_USD = "https://cex.io/api/last_price/ETH/USD";
//    static final String URL_XRP_USD = "https://cex.io/api/last_price/XRP/USD";
//
    private RestTemplate restTemplate = new RestTemplate();
//
//    ResponseEntity<List> responseBTC = restTemplate.getForEntity(URL_BTC_USD, List.class);
//    List currencyBTC = responseBTC.getBody();

    public CurrencyDTO getCurr() {
        String url = "https://cex.io/api/last_price/BTC/USD";
        try {
            CurrencyDTO response = restTemplate.getForObject(new URI(url), CurrencyDTO.class);
            return response;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
