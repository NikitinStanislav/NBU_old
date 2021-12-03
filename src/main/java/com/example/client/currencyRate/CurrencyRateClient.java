package com.example.client.currencyRate;

import com.example.client.currency.CurrencyRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@Slf4j
public class CurrencyRateClient {

    /** RestTemplate bean is created in Configuration by means of RestTemplateBuilder */
    @Autowired
    private RestTemplate restTemplate;


    @Value("${com.example.client.url}")
    private String url;


    /**Returns empty CurrencyRateRecord if not correct abbreviation provided*/
    public CurrencyRateRecord getCurrencyRateRecord(String abbreviation, String date){

        String fullUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("valcode", abbreviation)
                .queryParam("date", date)
                .queryParam("json").toUriString();

        log.info("ResponseEntity creating");
        ResponseEntity<List<CurrencyRateRecord>> response = restTemplate.exchange(fullUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CurrencyRateRecord>>() {
                });

        if(response.getBody().size()==0) log.error("ResponseEntity is empty. Check correct currency name in query. Empty record created");

        return response.getBody().stream().findAny().orElse(new CurrencyRateRecord());
    }
}
