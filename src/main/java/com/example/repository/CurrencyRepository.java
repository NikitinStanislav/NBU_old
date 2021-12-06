package com.example.repository;

import com.example.domain.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

        Currency findByAbbreviation(String abbreviation);
}
