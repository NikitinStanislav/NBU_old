package com.example.client.currencyRate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRateRecord {

    private double rate;

    @Override
    public String toString() {
        return "CurrencyRateRecord{" +
                "rate=" + rate +
                '}';
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
