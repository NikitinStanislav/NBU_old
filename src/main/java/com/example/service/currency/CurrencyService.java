package com.example.service.currency;

import com.example.client.currency.CurrencyClient;
import com.example.client.currency.CurrencyRecord;
import com.example.domain.Currency;
import com.example.domain.CurrencyRate;
import com.example.dto.CurrencyDTO;
import com.example.dto.CurrencyRateDTO;
import com.example.repository.CurrencyRepository;
import com.example.service.currencyRate.CurrencyRateService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    @Autowired
    private CurrencyRateService currencyRateService;

    /**Returns specified currency type data from "currency" table*/
    public List<CurrencyDTO> findExactCurrencyByValcode(String abbreviation) {
        if(abbreviation==null){
            return getDTOFromEntities(getFullListOfCurrencies());
        }
        return getDTOFromEntities(Arrays.asList(currencyRepository.findByAbbreviation(abbreviation)));
    }

    /**Returns specified currency type data from "currency" table*/
    public List<CurrencyDTO> findExactCurrencyById(long id){
        List<Currency>list = new ArrayList<>();
        list.add(currencyRepository.findById(id).get());   //если будет выделываться, добавить проверку isPresent();
        return getDTOFromEntities(list);
    }

    /**Saves specified currency type in "currency" table*/
    public void saveCurrency(List<CurrencyDTO> currencyDTO){
        for(CurrencyDTO cur: currencyDTO) {
            String abbreviation = cur.getAbbreviation();
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
        currencyRateService.deleteCurrencyRates(currencyRepository.findById(id).get());
        currencyRepository.deleteById(id);
    }

    public List<CurrencyDTO> getCurrencyByDate(long id, LocalDate date){
        LocalDate localDate = date==null ? LocalDate.now() : date;
        Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        List<CurrencyDTO> result = findExactCurrencyById(id);

        for(CurrencyDTO currencyDTO: result) {
            currencyDTO.getCurrencyRate().removeIf(currencyRateDTO -> !currencyRateDTO.getDate().equals(instant));
        }
        return result;
    }

    /**Returns list of all currency types saved in "currency" table*/
    private List<Currency> getFullListOfCurrencies() {
        Iterable<Currency> iters = currencyRepository.findAll();
        List<Currency> list = new ArrayList<>();
        for(Currency cur: iters){
            list.add(cur);
        }
        return list;
    }

    private List<CurrencyDTO> getDTOFromEntities(List<Currency>currencies){
        List<CurrencyDTO> result = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for(Currency cur: currencies){
            CurrencyDTO currencyDTO = modelMapper.map(cur, CurrencyDTO.class);
            result.add(currencyDTO);
        }
        return result;
    }


    /**Method for CurrencyRateService*/
    public Currency getCurrencyFromAbbreviation(String abbreviation){
        return currencyRepository.findByAbbreviation(abbreviation);
    }
}
