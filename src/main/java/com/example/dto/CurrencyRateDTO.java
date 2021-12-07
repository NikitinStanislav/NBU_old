package com.example.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class CurrencyRateDTO {
    private long id;
    private double rate;
    private Instant date;
}
