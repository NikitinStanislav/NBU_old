package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class NBUClient {

    @Autowired private RestTemplate restTemplate;

    @Autowired private String URL;

    public List<CurrencyRecord> getCurrencies() {
        log.info("getting list of CurrencyRecords");
        ResponseEntity<List<CurrencyRecord>> response;
        List<CurrencyRecord> list = new ArrayList<>();
        try {
            response = restTemplate.exchange(URL, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<CurrencyRecord>>() {
                    });
            list = response.getBody();
        } catch (Error error){
            log.error("CurrencyRecords not received");
        }
        return list;
    }
}
