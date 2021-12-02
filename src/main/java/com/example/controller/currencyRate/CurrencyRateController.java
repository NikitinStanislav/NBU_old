package com.example.controller.currencyRate;

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

    @GetMapping
    public List<String> list(){
        return currencyService.getList();
    }

    @GetMapping("/{abbreviation}")
    public List<String> getOne(@PathVariable("abbreviation") String abbreviation){
        return currencyService.findExactCurrency(abbreviation);
    }

    @PostMapping
    public void save(@RequestParam("valcode") String abbreviation,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        currencyService.saveCurrencyRate(abbreviation, date);
    }
}