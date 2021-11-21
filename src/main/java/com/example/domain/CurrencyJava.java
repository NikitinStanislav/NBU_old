package com.example.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name="Supported_Currencies")
@JsonIgnoreProperties(ignoreUnknown=true)   // indicate that any properties not bound in this type should be ignored.
public class CurrencyJava {    //возможно Serializable понадобится
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cc;
    private int r030;
    private int rate;


    public CurrencyJava() {
    }

    public CurrencyJava(String cc, int r030, int rate) {
        this.cc = cc;
        this.r030 = r030;
        this.rate = rate;
    }
    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }


    public int getR030() {
        return r030;
    }


    public int getId() {
        return id;
    }

    public void setR030(int currency_code) {
        this.r030 = currency_code;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", cc='" + cc + '\'' +
                ", currency_code=" + r030 +
                ", rate=" + rate +
                '}';
    }
}
