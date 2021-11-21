package com.example.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NBU_Page {

    private int value;

    public int getValue() {
        return value;
    }

}
