package com.task.portfolio.portfolio.ServiceTests;

import com.task.portfolio.portfolio.Exception.NotEnoughStocksException;
import com.task.portfolio.portfolio.Exception.NotFoundException;
import com.task.portfolio.portfolio.dao.PortfolioDao;
import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.dao.UserDao;
import com.task.portfolio.portfolio.dto.TradeDTO;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.service.TradeServices;
import com.task.portfolio.portfolio.service.implementation.TradeServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TradeServiceTests {

    private UserDao userDao;
    private PortfolioDao portfolioDao;
    private StockDao stockDao;

    private TradeServices tradeServices;

    @BeforeEach
    void setup(){
        userDao = mock(UserDao.class);
        portfolioDao = mock(PortfolioDao.class);
        stockDao = mock(StockDao.class);

        tradeServices = new TradeServicesImpl(userDao,portfolioDao,stockDao);
    }

    @Test
    void NotEnoughStock_SellTest(){

        User user = new User();
        user.setId(1L);
        user.setName("harshil");
        user.setPan("0123456789");
        user.setPhoneNo("8128475144");
        user.setEmailId("h@gmail.com");

        Portfolio portfolio = new Portfolio();
        portfolio.setIsin("123");
        portfolio.setQuantity(1);
        portfolio.setBuyPrice(2.00);
        portfolio.setUser(user);



        when(userDao.getUser(1L)).thenReturn(user);
        when(portfolioDao.getPortfolio("123", user)).thenReturn(portfolio);

        assertThrows(NotEnoughStocksException.class, () -> {
            tradeServices.addTrade(new TradeDTO(1L,"123","sell",3));
        });
    }

    @Test
    void wrongTradeType_Test(){
        assertThrows(RuntimeException.class, () -> {
            tradeServices.addTrade(new TradeDTO(1L,"123","abs",3));
        });
    }
}
