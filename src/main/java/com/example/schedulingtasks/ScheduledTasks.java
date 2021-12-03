package com.example.schedulingtasks;

import com.example.domain.Currency;
import com.example.repository.CurrencyRepository;
import com.example.service.currency.CurrencyService;
import com.example.service.currencyRate.CurrencyRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Slf4j
@Component
public class ScheduledTasks {

    private static int counter = 1;

    @Autowired
    CurrencyRateService rateService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    CurrencyService currencyService;

    @Scheduled(cron = "0/3 * * * * *")
    public void someDemoAction(){
        Month month = Month.DECEMBER;
        if(counter==month.length(false)) counter=1;
        Iterable<Currency> list = currencyRepository.findAll();

        LocalDate localDate = LocalDate.of(2012, month, counter);

        for(Currency cur: list){
            rateService.saveCurrencyRate(cur.getAbbreviation(), localDate);
        }

            counter++;
    }



    /** Test assignment */
    @Scheduled(cron = "0 0 0 * * *")
    public void getRates(){
        Iterable<Currency> list = currencyRepository.findAll();
        for(Currency cur: list){
            rateService.saveCurrencyRate(cur.getAbbreviation(), null);
        }
        log.info("Daily saving EUR, USD and GBP rates complete");
    }
}
