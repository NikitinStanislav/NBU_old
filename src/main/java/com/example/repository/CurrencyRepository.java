package com.example.repository;

import com.example.domain.Currency;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Table;
import java.util.List;


public interface CurrencyRepository extends CrudRepository<Currency, Long> {

        Currency findByAbbreviation(String abbreviation);
}
