package com.example.client;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
@Component
public class Configuration {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    //TODO - config rest template bean here
    public RestTemplate restTemplate = (new RestTemplateBuilder()).build();
}
