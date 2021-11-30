package com.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@Slf4j
public class NBUClient {

    @Autowired private RestTemplate restTemplate;

    @Value("${com.example.client.url}")
    private String url;

    public List<CurrencyRecord> getCurrencies(String date){
        return getAllRecords(UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("date", date)
                .queryParam("json").toUriString());
    }

    private List<CurrencyRecord> getAllRecords(String url){
        ResponseEntity<List<CurrencyRecord>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CurrencyRecord>>() {
                });
        return response.getBody();
    }
}
