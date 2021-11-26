package com.example.repository;

import com.example.domain.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

        List<Currency> findByCode(int code);
}
