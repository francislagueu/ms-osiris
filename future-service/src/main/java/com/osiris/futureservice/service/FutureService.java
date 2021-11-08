package com.osiris.futureservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class FutureService {
    private RestTemplate restTemplate;
    @Value("${future.url}")
    private String futureUrl;

    @Autowired
    public FutureService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<ResponseEntity> getFuture(String symbol){
        String url = futureUrl + "?formatted=false&symbols=" + symbol;
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        return Optional.ofNullable(responseEntity);
    }
}
