package com.example.nbu_currency_rate_java;

import com.example.controller.NBU_Controller;
import com.example.domain.CurrencyJava;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SpringBootApplication
public class NbuCurrencyRateJavaApplication {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public static void main(String[] args) {
        SpringApplication.run(NbuCurrencyRateJavaApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();

        //restTemplate.setMessageConverters(getJsonMessageConverters());

        /*HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CurrencyJava[]> responseEntity = restTemplate.exchange(
                "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json",
                HttpMethod.GET,
                entity,
                CurrencyJava[].class);
                */
        /*ResponseEntity<CurrencyJava[]> responseEntity = restTemplate.getForEntity(
                "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json", CurrencyJava[].class);

        CurrencyJava [] currencyJava = responseEntity.getBody();*/
        CurrencyJava [] currencyJava = restTemplate.getForObject(URL, CurrencyJava[].class);



        for (CurrencyJava cur: currencyJava){
            System.out.println(cur.getRate()+" "+cur.getCc());
        }


    }

    private static List<HttpMessageConverter<?>> getJsonMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        return messageConverters;
    }

}
