package com.osiris.cryptoservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class CryptoService {
    private RestTemplate restTemplate;
    @Value("${crypto.url}")
    private String cryptoUrl;

    @Autowired
    public CryptoService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Optional<ResponseEntity> getCryptos(){
        String url = cryptoUrl + "?formatted=false&scrIds=all_cryptocurrencies_us&start=0";
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        return Optional.ofNullable(responseEntity);
    }
}
