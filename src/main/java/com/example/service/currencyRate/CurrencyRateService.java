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

    /**Saves specified currency rate on specified date. Specified currency MUST be from "currency" table
     * If date is not specified method will return currency rate for present day*/
    public void saveCurrencyRate(Currency currency, LocalDate date) {

        if(currency==null) return;

        LocalDate localDate = date==null ? LocalDate.now() : date;
        String dateString = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        CurrencyRateRecord curRateRec = client.getCurrencyRateRecord(currency.getAbbreviation(), dateString);
            try{
        currencyRateRepository.save(new CurrencyRate(curRateRec.getRate()
                , localDate.atStartOfDay().toInstant(ZoneOffset.UTC)
                , currency));
            } catch (DataIntegrityViolationException exception) {
            log.info("Trying to add a duplicating values: currency " + currency.getAbbreviation()+", date "+date);
            }
        }

    public void saveCurrencyRateFromAbbreviation(String abbreviation, LocalDate date){
        Currency currency = currencyService.getCurrencyFromAbbreviation(abbreviation);
        saveCurrencyRate(currency ,date);
    }


    public void deleteCurrencyRates(Currency currency){
        List <CurrencyRate> cur = currencyRateRepository.findAllByCurrency(currency);
        currencyRateRepository.deleteAll(cur);
    }
}
