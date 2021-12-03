package com.example.controller.currencyRate;

import com.example.domain.CurrencyRate;
import com.example.service.currency.CurrencyService;
import com.example.service.currencyRate.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/currency-rate")
public class CurrencyRateController {

    @Autowired
    private CurrencyRateService currencyService;

    /**Returns list of all records saved in "currency_rate" table
     * */
    @GetMapping
    public List<String> list(){
        return currencyService.getAllRecords();
    }

    /**Returns specified currency rate on specified date. Specified currency MUST be from currency table
     * If date is not specified method will return currency rate for present day
     * */
    @GetMapping("/{valcode}")
    public String getOne(@PathVariable("valcode") String abbreviation){
        return currencyService.getCurrencyRate(abbreviation).toString();
    }

    /**Saves specified currency rate on specified date. Specified currency MUST be from currency table
     * If date is not specified method will return currency rate for present day*/
    @PostMapping
    public void save(@RequestParam("valcode") String abbreviation,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        currencyService.saveCurrencyRate(abbreviation, date);
    }
}