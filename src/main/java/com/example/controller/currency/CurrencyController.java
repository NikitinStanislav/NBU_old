package com.example.controller.currency;

import com.example.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<String> list(){
        return currencyService.getList();
    }

    @GetMapping("/{abbreviation}")
    public String getOne(@PathVariable("abbreviation") String abbreviation){
        return currencyService.findExactCurrency(abbreviation);
    }

    @PostMapping
    public void save(@RequestParam("valcode") String abbreviation){
        currencyService.saveCurrency(abbreviation);
    }
}