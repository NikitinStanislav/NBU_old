package com.example.client;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
@Component
@Slf4j
public class Configuration {

    @Bean
    public String URL(){
        log.info("URL getted");
        return "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        log.info("RestTemplateBuilder creating new RestTemplate");
        return builder.build();
    }

}
