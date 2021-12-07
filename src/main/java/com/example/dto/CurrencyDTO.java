package com.example.dto;

import com.example.domain.CurrencyRate;
import lombok.Data;

import java.util.List;

@Data
public class CurrencyDTO {
    private String abbreviation;
    private int code;
    private String name;
    private List<CurrencyRateDTO> currencyRate;
    private long id;
}
