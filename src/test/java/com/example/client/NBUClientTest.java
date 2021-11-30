package com.example.client;

import com.example.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NBUClientTest {

    @Autowired NBUClient client;

    @Test
    public void test() {
       assertThat(client.getCurrencies("20181111").size()).isGreaterThan(30);
    }

}
