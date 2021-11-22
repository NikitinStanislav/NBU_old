package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class NBUClient {

    @Autowired Configuration configuration = new Configuration();

    public void printRecords(){
        //List<CurrencyRecord> list= configuration.getData();
        //for(CurrencyRecord cur: list)
        //System.out.println(cur.toString());
    }

    public CurrencyRecord[] getCurrencies() {
        return null;
    }
}
