package com.example.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NBUClientTest {

    @Autowired NBUClient client;

    @Test
    public void test() {
        assertThat(client.getCurrencies().size()).isGreaterThan(30);
    }
}
