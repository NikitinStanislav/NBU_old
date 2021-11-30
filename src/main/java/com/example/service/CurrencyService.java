package com.example.service;


import com.example.client.CurrencyRecord;
import com.example.client.NBUClient;
import com.example.domain.Currency;
import com.example.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService{

    @Autowired
    private NBUClient client;

    @Autowired
    private CurrencyRepository currencyRepository;

    public String findExactCurrency(int code){
        return currencyRepository.findByCode(code).toString();
    }

    public List<String> getList(){
        Iterable<Currency> list = currencyRepository.findAll();
        List<String> strings = new ArrayList<>();
        for(Currency cur : list){
            strings.add(cur.toString());
        }
        return strings;
    }

    public void saveList(LocalDate date){

        LocalDate localDate = date==null ? LocalDate.now() : date;
        String dateString = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<CurrencyRecord> records = client.getCurrencies(dateString);
        List<Currency> currencies = new ArrayList<>();
        for (CurrencyRecord cur : records){
            currencies.add(new Currency(cur.getAbbreviation(),
                    cur.getRate(), cur.getCode(), cur.getName(), localDate.atStartOfDay().toInstant(ZoneOffset.UTC)));
        }

        currencyRepository.saveAll(currencies);
    }
}
