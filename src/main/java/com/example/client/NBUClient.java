package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class NBUClient {

    @Autowired Configuration configuration = new Configuration();

    public List<CurrencyRecord> getCurrencies() {
        return null;
    }
}
