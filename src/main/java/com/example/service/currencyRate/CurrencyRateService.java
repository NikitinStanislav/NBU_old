package com.example.service.currencyRate;


import com.example.client.currency.CurrencyClient;
import com.example.client.currency.CurrencyRecord;
import com.example.client.currencyRate.CurrencyRateClient;
import com.example.client.currencyRate.CurrencyRateRecord;
import com.example.domain.Currency;
import com.example.domain.CurrencyRate;
import com.example.repository.CurrencyRateRepository;
import com.example.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyRateService {

    @Autowired
    private CurrencyRateClient client;

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    @Autowired
    private CurrencyRepository  currencyRepository;

    public List<String> findExactCurrency(String abbreviation){
        Currency currency = currencyRepository.findByAbbreviation(abbreviation);
        List<CurrencyRate> list = currencyRateRepository.findByCurrency(currency);
        List<String> strings = new ArrayList<>();
        for (CurrencyRate cur: list)
            strings.add(cur.toString());

        return strings;
    }
    public String findExactCurrencyByDate(String abbreviation, LocalDate date){

        LocalDate localDate = date==null ? LocalDate.now() : date;
        Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);

        Currency currency = currencyRepository.findByAbbreviation(abbreviation);
        List<CurrencyRate> list = currencyRateRepository.findByCurrency(currency);

        return list.stream().filter(x->x.getDate().equals(instant)).collect(Collectors.toList()).stream().findFirst().toString();
    }

    public List<String> getList(){
        Iterable<CurrencyRate> list = currencyRateRepository.findAll();
        List<String> strings = new ArrayList<>();
        for(CurrencyRate cur : list){
            strings.add(cur.toString());
        }
        return strings;
    }

    public void saveCurrencyRate(String abbreviation, LocalDate date){

        LocalDate localDate = date==null ? LocalDate.now() : date;
        String dateString = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        CurrencyRateRecord curRateRec = client.getCurrencyRateRecord(abbreviation, dateString);
        Currency currency = currencyRepository.findByAbbreviation(abbreviation);

        if(currency!=null)
        currencyRateRepository.save(new CurrencyRate(curRateRec.getRate()
                , localDate.atStartOfDay().toInstant(ZoneOffset.UTC)
                , currency));
    }
}
