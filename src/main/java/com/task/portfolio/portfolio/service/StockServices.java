package com.task.portfolio.portfolio.service;

import com.task.portfolio.portfolio.entity.sql.Stock;
import org.springframework.web.multipart.MultipartFile;

public interface StockServices {

    public void updateStock(MultipartFile file);
    public boolean isCsv(MultipartFile file);
    public Stock getStock(String id);
}