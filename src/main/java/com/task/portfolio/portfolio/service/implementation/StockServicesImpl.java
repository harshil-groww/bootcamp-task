package com.task.portfolio.portfolio.service.implementation;

import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.helper.CsvHelper;
import com.task.portfolio.portfolio.service.StockServices;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@RequiredArgsConstructor
public class StockServicesImpl implements StockServices {

    private final StockDao stockDao;
    private final CsvHelper csvHelper;
    @Override
    public void updateStock(MultipartFile file) {
        csvHelper.updateStocksFromCSV(file);
    }

    @Override
    public boolean isCsv(MultipartFile file) {
        return csvHelper.isCSV(file);
    }

    @Override
    public Stock getStock(String id){
        return stockDao.getStock(id);
    }
}
