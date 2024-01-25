package com.task.portfolio.portfolio.dao;

import com.task.portfolio.portfolio.Exception.NotFoundException;
import com.task.portfolio.portfolio.dto.StockDTO;
import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StockDao {
    private final StockRepository stockRepository;

    public Stock getStock(String id) {
        Optional<Stock> stock = stockRepository.findById(id);

        if(stock.isEmpty())
        {
            throw new NotFoundException("stock not found");
        }
        return stock.get();
    }

    public Double getOpen (String id){
        Stock stock = getStock(id);

        return stock.getOpen();
    }

    public boolean stockIsAvailable(String isin)
    {
        Optional<Stock> stock = stockRepository.findById(isin);
        return stock.isPresent();
    }
    public void updateStock(StockDTO stockDTO)
    {
        Stock stock = null;

        if(!stockIsAvailable(stockDTO.getIsin()))
        {
            stock = new Stock();
            stock.setId(stockDTO.getIsin());
            stock.setName(stockDTO.getName());
        }
        else {
            stock = getStock(stockDTO.getIsin());
        }

        stock.setHigh(stockDTO.getHigh());
        stock.setClose(stockDTO.getClose());
        stock.setOpen(stockDTO.getOpen());
        stock.setLow(stockDTO.getLow());

        stockRepository.save(stock);
    }


}