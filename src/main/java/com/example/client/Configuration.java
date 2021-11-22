package com.example.client;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@org.springframework.context.annotation.Configuration
@Component
public class Configuration {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public RestTemplate restTemplate = (new RestTemplateBuilder()).build();

    public String getData(){
        /* Could not extract response: no suitable HttpMessageConverter  - возможно из-за того что текст украинский внутри
        я пробовал и через обычный массив и список создал отдельным классом каждый раз одно и то же
        с этого момента делаю через  String
        RecordsList records = restTemplate.getForObject(URL, RecordsList.class);
        return records;*/

        /*это еще одна попытка была
        //ResponseEntity<RecordsList> responseEntity = restTemplate.getForEntity(URL, RecordsList.class);
        return responseEntity.getBody();*/

        //и еще один способ
        //ResponseEntity<List<CurrencyRecord>> responseEntity = restTemplate.exchange(URL,
               // HttpMethod.GET, null, new ParameterizedTypeReference<List<CurrencyRecord>>() {
              //  });
       // return responseEntity.getBody();


                ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL, String.class);
        return responseEntity.getBody();



    }


}
