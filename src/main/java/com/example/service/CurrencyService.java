package com.example.service;


import com.example.client.CurrencyRecord;
import com.example.client.NBUClient;
import com.example.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService{

    @Autowired
    private NBUClient client;

    public List<String> getList(){
        List<CurrencyRecord>list = client.getCurrencies();
        return list.stream().map(CurrencyRecord::toString).collect(Collectors.toList());
    }

    public List<CurrencyRecord> getRecords(){ return client.getCurrencies();}

    public String getOne(int code){
        List<CurrencyRecord>list = client.getCurrencies();
        for(CurrencyRecord record: list) {
            if (record.getCode()==(code))
                return record.toString();
        }
        return "No currency for this code number";
    }

    public List<Currency> getCurrencyList(){
        List<CurrencyRecord> records = client.getCurrencies();
        List<Currency> currencies = new ArrayList<>();
        for (CurrencyRecord cur : records){
            currencies.add(new Currency(cur.getAbbreviation(),
                    cur.getRate(), cur.getCode(), cur.getName()));
        }
        return currencies;
    }
}
