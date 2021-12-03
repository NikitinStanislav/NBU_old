package com.example.service.currency;

import com.example.client.currency.CurrencyClient;
import com.example.client.currency.CurrencyRecord;
import com.example.domain.Currency;
import com.example.dto.CurrencyDTO;
import com.example.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CurrencyService{

    /** Provides access to bank data*/
    @Autowired
    private CurrencyClient client;

    /** Additional Currency findByAbbreviation(String abbreviation) method provided*/
    @Autowired
    private CurrencyRepository currencyRepository;

    /**Returns specified currency type data from "currency" table*/
    public List<String> findExactCurrencyByValcode(String abbreviation){
        if(abbreviation==null){
            return getList();
        }
        List<String> result = new ArrayList<>();
        try {
            result.add(currencyRepository.findByAbbreviation(abbreviation).toString());
        } catch (NullPointerException npe){
            log.warn("No such type of currency in the table");
        }
        return result;
    }

    public String findExactCurrencyById(long id){
            String result = currencyRepository.findById(id).toString();
            if(result.equals("Optional.empty"))
            log.warn("No such currency id in the table");
        return result;
    }

    /**Saves specified currency type in "currency" table*/
    public void saveCurrency(List<CurrencyDTO> currencyDTO){
        for(CurrencyDTO cur: currencyDTO) {
            String abbreviation = cur.getValcode();
            CurrencyRecord curRec = client.getCurrencyRecord(abbreviation);
            try {
                currencyRepository.save(new Currency(abbreviation, curRec.getCode(), curRec.getName()));
            } catch (DataIntegrityViolationException exception) {
                log.info("Trying to add a duplicate currency: " + abbreviation);
            } catch (NullPointerException npe) {
                log.warn("Wrong name. Currency " + abbreviation + " not added");
            }
        }
    }

    /**Delete specified currency type from "currency" table*/
    public void deleteCurrency(Long id){
            currencyRepository.deleteById(id);
    }

    /**Returns list of all currency types saved in "currency" table*/
    private List<String> getList(){
        Iterable<Currency> list = currencyRepository.findAll();
        List<String> strings = new ArrayList<>();
        for(Currency cur : list){
            strings.add(cur.toString());
        }
        return strings;
    }
}
