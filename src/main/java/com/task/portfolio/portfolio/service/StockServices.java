package com.task.portfolio.portfolio.service;

import com.task.portfolio.portfolio.entity.sql.Stock;

public interface StockServices {

    public void updateStock(String id);

    public Stock getStock(String id);
}