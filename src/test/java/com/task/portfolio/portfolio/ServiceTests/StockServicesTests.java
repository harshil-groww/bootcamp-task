package com.task.portfolio.portfolio.ServiceTests;

import com.task.portfolio.portfolio.Exception.NotFoundException;
import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.dto.TradeDTO;
import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.helper.CsvHelper;
import com.task.portfolio.portfolio.service.StockServices;
import com.task.portfolio.portfolio.service.implementation.StockServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StockServicesTests {

    private StockDao stockDao;
    private CsvHelper csvHelper;
    private StockServices stockServices;

    @BeforeEach
    void setup(){
        stockDao = mock(StockDao.class);
        csvHelper = mock(CsvHelper.class);

        stockServices = new StockServicesImpl(stockDao, csvHelper);
    }

    @Test
    void stockNotPresentAndGetById_Test(){

        when(stockDao.getStock("abc")).thenThrow(new NotFoundException("stock not found"));

        assertThrows(NotFoundException.class, () -> {
            stockServices.getStock("abc");
        });
    }

    @Test
    void StockIsPresentAndGetById_Test(){

        Stock stock = new Stock("123","abc",1.0,2.0,0.5,1.5);

        when(stockDao.getStock("123")).thenReturn(stock);

        Stock resultStock = stockServices.getStock("123");

        assertEquals(stock, resultStock);
    }



}
