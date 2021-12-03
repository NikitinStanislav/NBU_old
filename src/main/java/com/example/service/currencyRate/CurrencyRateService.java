package com.example.service.currencyRate;

import com.example.client.currencyRate.CurrencyRateClient;
import com.example.client.currencyRate.CurrencyRateRecord;
import com.example.domain.Currency;
import com.example.domain.CurrencyRate;
import com.example.repository.CurrencyRateRepository;
import com.example.repository.CurrencyRepository;
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

    /** Additional Currency findByAbbreviation(String abbreviation) method provided*/
    @Autowired
    private CurrencyRepository  currencyRepository;

    /**Returns specified currency rate on specified date. Specified currency MUST be from "currency" table
     * If date is not specified method will return currency rate for present day*/
    public CurrencyRate getCurrencyRate(String abbreviation){

        return currencyRateRepository.findByCurrency(currencyRepository.findByAbbreviation(abbreviation));
    }

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
    public void saveCurrencyRate(String abbreviation, LocalDate date){

        LocalDate localDate = date==null ? LocalDate.now() : date;
        String dateString = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        CurrencyRateRecord curRateRec = client.getCurrencyRateRecord(abbreviation, dateString);
        try {
            currencyRateRepository.delete(getCurrencyRate(abbreviation));
        } catch (Exception exception){
            log.info("New rate specified");
        }
        Currency currency = currencyRepository.findByAbbreviation(abbreviation);
        if(currency!=null)

                currencyRateRepository.save(new CurrencyRate(curRateRec.getRate()
                        , localDate.atStartOfDay().toInstant(ZoneOffset.UTC)
                        , currency));

    }
}
