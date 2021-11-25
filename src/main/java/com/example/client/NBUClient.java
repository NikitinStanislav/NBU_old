package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${com.example.client.url}")
    private String URL;

    public List<CurrencyRecord> getCurrencies() {
        log.info("getting list of CurrencyRecords");
        ResponseEntity<List<CurrencyRecord>> response;
        List<CurrencyRecord> list = new ArrayList<>();
        try {
            response = restTemplate.exchange(URL, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<CurrencyRecord>>() {
                    });
            list = response.getBody();

        } catch (Exception ex){
            log.error("CurrencyRecords not received", ex);
        }
        return list;
    }
}
