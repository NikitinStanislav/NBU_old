package com.example.client.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRecord {

    @JsonProperty("cc")
    private String abbreviation;
    @JsonProperty("r030")
    private int code;
    @JsonProperty("txt")
    private String name;

    @Override
    public String toString() {
        return "CurrencyRecord{" +
                "abbreviation='" + abbreviation + '\'' +
                ", code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
