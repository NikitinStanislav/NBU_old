package com.example.client;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NBUClientTest {

    @Autowired NBUClient client;

    @Test
    public void test() {
       assertThat(client.getCurrencies().size()).isGreaterThan(30);
    }

}
