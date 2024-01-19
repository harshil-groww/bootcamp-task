package com.task.portfolio.portfolio.dao;

import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.repository.StockRepository;
import org.springframework.stereotype.Component;

@Component
public class StockDao {
    private StockRepository stockRepository;

    public Stock getStock(String id) {
        return stockRepository.getById(id);
    }
}