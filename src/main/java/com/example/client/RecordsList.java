package com.example.client;

import java.util.ArrayList;
import java.util.List;

public class RecordsList {
    private List<CurrencyRecord> listOfRecords;

    public RecordsList() {
        this.listOfRecords = new ArrayList<>();
    }

    public List<CurrencyRecord> getListOfRecords() {
        return listOfRecords;
    }

    public void setListOfRecords(List<CurrencyRecord> listOfRecords) {
        this.listOfRecords = listOfRecords;
    }
}
