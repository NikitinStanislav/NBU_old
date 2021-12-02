package com.example.repository;

import com.example.domain.Currency;
import com.example.domain.CurrencyRate;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Table;
import java.util.List;

@Table(name ="currencyRate")
public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, Long> {

        List<CurrencyRate> findByCurrency(Currency currency);
}
