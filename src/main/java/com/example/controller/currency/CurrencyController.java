package com.example.controller.currency;

import com.example.domain.Currency;
import com.example.dto.CurrencyDTO;
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


    /**Returns list of all types of currency saved in "currency" table if valcode is not specified*/
    /**Returns specified type of currency from "currency" table*/
    @GetMapping()
    public List<CurrencyDTO> getCurrencies(@RequestParam(value = "valcode", required = false) String abbreviation,
                                       @RequestParam(value = "id", required = false) Long id){
        return id==null? currencyService.findExactCurrencyByValcode(abbreviation) : currencyService.findExactCurrencyById(id);
    }

    @GetMapping("/{id}")
    public List<CurrencyDTO> getCurrencyByDate(@PathVariable("id") long id,
                        @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return currencyService.getCurrencyByDate(id, date);
    }

    @PostMapping
    @ResponseBody
    public void save(@RequestBody List<CurrencyDTO> currencyDTO) {
        currencyService.saveCurrency(currencyDTO);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable("id") long id){
        currencyService.deleteCurrency(id);
    }

}