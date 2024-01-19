package com.task.portfolio.portfolio.entity.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stock {

    @Id
    private String Id;
    private String Name;
    private Double Open;
    private Double High;
    private Double Low;
    private Double Close;

    public Stock(String id, String name, Double open, Double high, Double low, Double close, Long trades) {
        Isin = id;
        Name = name;
        Open = open;
        High = high;
        Low = low;
        Close = close;
        Trades = trades;
    }

    public String getIsin() {
        return Id;
    }

    public void setIsin(String isin) {
        Id = isin;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getOpen() {
        return Open;
    }

    public void setOpen(Double open) {
        Open = open;
    }

    public Double getHigh() {
        return High;
    }

    public void setHigh(Double high) {
        High = high;
    }

    public Double getLow() {
        return Low;
    }

    public void setLow(Double low) {
        Low = low;
    }

    public Double getClose() {
        return Close;
    }

    public void setClose(Double close) {
        Close = close;
    }

    public Long getTrades() {
        return Trades;
    }

    public void setTrades(Long trades) {
        Trades = trades;
    }

    private Long Trades;
}
