package com.example.controller.currency;

import com.example.domain.Currency;
import com.example.dto.CurrencyDTO;
import com.example.service.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;


    /**Returns list of all types of currency saved in "currency" table if valcode is not specified*/
    /**Returns specified type of currency from "currency" table*/
    @GetMapping()
    @ResponseBody
    public List<Currency> getCurrencies(@RequestParam(value = "valcode", required = false) String abbreviation,
                                       @RequestParam(value = "id", required = false) Long id){
        return id==null? currencyService.findExactCurrencyByValcode(abbreviation) : currencyService.findExactCurrencyById(id);
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