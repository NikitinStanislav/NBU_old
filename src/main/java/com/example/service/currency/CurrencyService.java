package com.example.service.currency;


import com.example.client.currency.CurrencyRecord;
import com.example.client.currency.CurrencyClient;
import com.example.domain.Currency;
import com.example.domain.CurrencyRate;
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
    private CurrencyClient client;

    @Autowired
    private CurrencyRepository currencyRepository;

    public String findExactCurrency(String abbreviation){
        return currencyRepository.findByAbbreviation(abbreviation).toString();
    }

    public List<String> getList(){
        Iterable<Currency> list = currencyRepository.findAll();
        List<String> strings = new ArrayList<>();
        for(Currency cur : list){
            strings.add(cur.toString());
        }
        return strings;
    }

    public void saveCurrency(String abbreviation){
        CurrencyRecord curRec = client.getCurrencyRecord(abbreviation);
        currencyRepository.save(new Currency(abbreviation, curRec.getCode(), curRec.getName()));
    }
}
