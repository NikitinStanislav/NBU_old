package com.example;

import com.example.client.NBUClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class Application {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        NBUClient client = new NBUClient();
        client.printRecords();


        //RestTemplate restTemplate = new RestTemplate();

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
        /*ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                URL, String.class);

        String string = responseEntity.getBody();
        System.out.println(string);*/
        //CurrencyJava [] currencyJava = responseEntity.getBody();

//        CurrencyJava [] currencyJava = restTemplate.getForObject(URL, CurrencyJava[].class);
//
//
//
//        for (CurrencyJava cur: currencyJava){
//            System.out.println(cur.getRate()+" "+cur.getCc());
//        }
//
//
//    }
//
//    private static List<HttpMessageConverter<?>> getJsonMessageConverters() {
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
//        messageConverters.add(converter);
//        return messageConverters;
//    }
    }
}
