package com.task.portfolio.portfolio.service.implementation;

import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.service.StockServices;
import lombok.Builder;

@Builder
public class StockServicesImpl implements StockServices {

    private final StockDao stockDao;
    @Override
    public void updateStock(String id) {

    }

    @Override
    public Stock getStock(String id){
        return stockDao.getStock(id);
    }
}
