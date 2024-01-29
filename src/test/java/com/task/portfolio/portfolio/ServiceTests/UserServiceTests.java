package com.task.portfolio.portfolio.ServiceTests;

import com.task.portfolio.portfolio.Exception.NotFoundException;
import com.task.portfolio.portfolio.ResponseDTO.Holdings;
import com.task.portfolio.portfolio.ResponseDTO.PortfolioResponse;
import com.task.portfolio.portfolio.dao.PortfolioDao;
import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.dao.UserDao;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.service.UserServices;
import com.task.portfolio.portfolio.service.implementation.UserServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

//    @Mock
    private PortfolioDao portfolioDao;
//
//    @Mock
    private StockDao stockDao;
    private UserDao userDao;
//
//    @InjectMocks
    private UserServicesImpl userServices;

    @BeforeEach
    void setup(){
        stockDao = mock(StockDao.class);
        portfolioDao = mock(PortfolioDao.class);
        userDao = mock(UserDao.class);
        userServices = new UserServicesImpl(portfolioDao, stockDao,userDao);
    }
    @Test
    void UserHasNotTradedTest(){
        Long userId = 1L;
        List<Portfolio> portfolios = new ArrayList<>();
        List<Holdings> portfolioResponses = new ArrayList<>();

        doReturn(portfolios).when(portfolioDao).getAllPortfolios(userId);

        PortfolioResponse portfolioResponse = userServices.getPortfolio(userId);

        assertEquals(portfolioResponses, portfolioResponse.getHoldings());

        assertEquals(0.0, portfolioResponse.getTotalBuyPrice());
        assertEquals(0.0, portfolioResponse.getTotalHoldings());
        assertEquals(0.0, portfolioResponse.getTotalProfitLoss());
        assertEquals(0.0, portfolioResponse.getTotalProfitPercentage());
    }

    @Test
    void UserDoesNotExistTest(){
        Long userId = 1L;
        List<Portfolio> portfolios = new ArrayList<>();
        List<Holdings> portfolioResponses = new ArrayList<>();

        doThrow(new NotFoundException("user not found")).when(portfolioDao).getAllPortfolios(userId);


        assertThrows(NotFoundException.class, () -> {
            portfolioDao.getAllPortfolios(userId);
        });

        //ask why

    }

    @Test
    void UserPresentAndTradedTest(){
        Long userId = 1L;
        List<Portfolio> portfolios = new ArrayList<>();

        User user = new User();
        user.setId(1L);
        user.setName("harshil");
        user.setPan("0123456789");
        user.setPhoneNo("8128475144");
        user.setEmailId("h@gmail.com");

        portfolios.add(new Portfolio("123",1,4.00, user));
        portfolios.add(new Portfolio("456",2,2.50, user));

        List<Holdings> portfolioResponses = new ArrayList<>();
        portfolioResponses.add(new Holdings("abc", "123", 1, 4.00, 1.0, -3.00));
        portfolioResponses.add(new Holdings("def", "456", 2, 2.50, 5.0, 5.00));

        doReturn(portfolios).when(portfolioDao).getAllPortfolios(userId);
//        holding.setName((stockDao.getStock(portfolio.getIsin())).getName());
//        holding.setId(portfolio.getIsin());
//        holding.setQuantity(portfolio.getQuantity());
//        holding.setBuyPrice(portfolio.getBuyPrice());
//        holding.setCurrentPrice(stockDao.getOpen(portfolio.getIsin()));
//        holding.setGainLoss(stockDao.getOpen(portfolio.getIsin())-portfolio.getBuyPrice());
        doReturn(new Stock("123","abc",1.0,2.0,0.5,1.5)).when(stockDao).getStock("123");
        doReturn(new Stock("456","def",5.0,6.0,4.5,5.5)).when(stockDao).getStock("456");

        doReturn(5.0).when(stockDao).getOpen("456");
        doReturn(1.0).when(stockDao).getOpen("123");

        PortfolioResponse portfolioResponse = userServices.getPortfolio(userId);

        assertEquals(portfolioResponses, portfolioResponse.getHoldings());

        assertEquals(9.0, portfolioResponse.getTotalBuyPrice());
        assertEquals(11.0, portfolioResponse.getTotalHoldings());
        assertEquals(2.0, portfolioResponse.getTotalProfitLoss());
        assertEquals(22.22, portfolioResponse.getTotalProfitPercentage());
    }
}
