package com.example.client;

import com.example.client.currencyRate.CurrencyRateClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NBUClientTest {

    @Autowired
    CurrencyRateClient client;

    @Test
    public void test() {
       client.getCurrencyRateRecord("EUR", "20201110");
    }

}
