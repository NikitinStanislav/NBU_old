package com.example.domain;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private double rate;
    @NotNull
    private Instant date;


    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    public CurrencyRate(@NonNull double rate, Instant date, Currency currency) {
        this.rate = rate;
        this.date = date;
        this.currency = currency;
    }

    public CurrencyRate() {

    }
}

