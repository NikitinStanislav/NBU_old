package com.example.repository;

import com.example.domain.Currency;
import com.example.domain.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;

@Table(name ="currencyRate")
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

        CurrencyRate findByCurrency(Currency currency);

        void deleteAllByCurrency(Currency currency);
}
