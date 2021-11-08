package com.osiris.currencyservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class CurrencyService {
    private RestTemplate restTemplate;
    @Value("${currency.url}")
    private String currencyUrl;

    @Autowired
    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<ResponseEntity> getCurrency(String symbol){
        String url = currencyUrl + "?range=1d&interval=5m&indicators=close&includeTimestamps=false&includePrePost=false&symbols=" + symbol;
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        return Optional.ofNullable(responseEntity);
    }

}
