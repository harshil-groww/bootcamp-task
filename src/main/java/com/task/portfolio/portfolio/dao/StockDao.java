package com.task.portfolio.portfolio.dao;

import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.repository.StockRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StockDao {
    private StockRepository stockRepository;

    public Stock getStock(String id) {
        Optional<Stock> stock = stockRepository.findById(id);

        if(stock.isEmpty())
        {

        }
        return stock.get();
    }

    public Double getOpen (String id){
        Stock stock = getStock(id);

        return stock.getOpen();
    }

}