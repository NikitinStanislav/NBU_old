package com.example.controller;

import com.example.domain.Currency;
import com.example.repository.CurrencyRepository;
import com.example.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping
    public List<String> list(){
        Iterable<Currency> list = currencyRepository.findAll();
        List<String> strings = new ArrayList<>();
        for(Currency cur : list){
            strings.add(cur.toString());
        }
        return strings;
    }

    @GetMapping("/{code}")
    public String getOne(@PathVariable("code") int code){
        return currencyRepository.findByCode(code).toString();
    }

    @PostMapping
    public void save(){
        List<Currency> currencies = currencyService.getCurrencyList();
        currencyRepository.saveAll(currencies);
    }


}
