package com.osiris.cryptoservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class CryptoService {
    private RestTemplate restTemplate;
    private CircuitBreakerFactory circuitBreakerFactory;
    @Value("${crypto.url}")
    private String cryptoUrl;

    @Autowired
    public CryptoService(RestTemplate restTemplate, CircuitBreakerFactory circuitBreakerFactory){
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public Optional<ResponseEntity> getCryptos(){
        String url = cryptoUrl + "?formatted=false&scrIds=all_cryptocurrencies_us&start=0";
        return circuitBreakerFactory.create("slow").run(()-> {
            ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
            return Optional.ofNullable(responseEntity);
        }, throwable -> Optional.ofNullable(fallback()));

    }

    private ResponseEntity fallback() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
