package com.example.service.currencyRate;

import com.example.client.currencyRate.CurrencyRateClient;
import com.example.client.currencyRate.CurrencyRateRecord;
import com.example.domain.Currency;
import com.example.domain.CurrencyRate;
import com.example.repository.CurrencyRateRepository;
import com.example.repository.CurrencyRepository;
import com.example.service.currency.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CurrencyRateService {

    /** Provides access to bank data*/
    @Autowired
    private CurrencyRateClient client;

    /** Additional List<CurrencyRate> findByCurrency(Currency currency) method provided*/
    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    @Autowired
    private CurrencyService currencyService;


    /**Returns list of all records saved in "currency_rate" table*/
    public List<String> getAllRecords(){
        Iterable<CurrencyRate> list = currencyRateRepository.findAll();
        List<String> strings = new ArrayList<>();
        for(CurrencyRate cur : list){
            strings.add(cur.toString());
        }
        return strings;
    }

    /**Saves specified currency rate on specified date. Specified currency MUST be from "currency" table
     * If date is not specified method will return currency rate for present day*/
    public void saveCurrencyRate(Currency currency, LocalDate date) {

        if(currency==null) return;

        LocalDate localDate = date==null ? LocalDate.now() : date;
        String dateString = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        CurrencyRateRecord curRateRec = client.getCurrencyRateRecord(currency.getAbbreviation(), dateString);

        currencyRateRepository.save(new CurrencyRate(curRateRec.getRate()
                , localDate.atStartOfDay().toInstant(ZoneOffset.UTC)
                , currency));
    }

    public void saveCurrencyRateFromAbbreviation(String abbreviation, LocalDate date){
        List<Currency>list = currencyService.findExactCurrencyByValcode(abbreviation);
        saveCurrencyRate(list.stream().findFirst().orElse(null) ,date);
    }


    public void deleteCurrencyRates(Currency currency){
        currencyRateRepository.deleteAllByCurrency(currency);
    }
}
