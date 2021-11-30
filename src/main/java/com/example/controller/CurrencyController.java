package com.example.controller;

import com.example.domain.Currency;
import com.example.repository.CurrencyRepository;
import com.example.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<String> list(){
        return currencyService.getList();
    }

    @GetMapping("/{code}")
    public String getOne(@PathVariable("code") int code){
        return currencyService.findExactCurrency(code);
    }

    @PostMapping
    public void save(@RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        currencyService.saveList(date);
    }
}