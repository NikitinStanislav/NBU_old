package com.example.schedulingtasks;

import com.example.domain.CurrencyRate;
import com.example.service.currencyRate.CurrencyRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
@Slf4j
public class ScheduledTasks {

    private static int counter = 1;

    @Autowired
    CurrencyRateService rateService;

    @Scheduled(fixedRate = 3000)
    public void someAction(){

        if(counter<31) {

            LocalDate localDate = LocalDate.of(2008, Month.AUGUST, counter);

            rateService.saveCurrencyRate("XAU", localDate);
            rateService.saveCurrencyRate("XAG", localDate);

            System.out.println(rateService.findExactCurrencyByDate("XAU", localDate));
            System.out.println(rateService.findExactCurrencyByDate("XAG", localDate));

            counter++;

            log.info("Scheduled update!");
        }
    }
}
