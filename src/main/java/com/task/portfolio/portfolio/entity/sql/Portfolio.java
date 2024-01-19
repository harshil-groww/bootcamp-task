package com.task.portfolio.portfolio.entity.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table
public class Portfolio {

    @Id
    private String TxnId;
    private String Isin;
    private Integer Quantity;
    private Double BuyPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public String getTxnId() {
        return TxnId;
    }

    public void setTxnId(String txnId) {
        TxnId = txnId;
    }

    public String getIsin() {
        return Isin;
    }

    public void setIsin(String isin) {
        Isin = isin;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Double getBuyPrice() {
        return BuyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        BuyPrice = buyPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Portfolio(String txnId, String isin, Integer quantity, Double buyPrice, User user) {
        TxnId = txnId;
        Isin = isin;
        Quantity = quantity;
        BuyPrice = buyPrice;
        this.user = user;
    }


}
