package com.task.portfolio.portfolio.service.implementation;

import com.task.portfolio.portfolio.dao.PortfolioDao;
import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.dao.UserDao;
import com.task.portfolio.portfolio.dto.TradeDTO;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
//import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.service.TradeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeServicesImpl implements TradeServices {

    private final UserDao userDao;
    private final PortfolioDao portfolioDao;
    private final StockDao stockDao;

    @Override
    public void sellTrade(TradeDTO tradeDTO) {

        User user = userDao.getUser(tradeDTO.getUserId());
        Portfolio portfolio = portfolioDao.getPortfolio(tradeDTO.getIsin(), user);

        Integer currentQnt = portfolio.getQuantity();
        Integer askedQnt = tradeDTO.getQuantity();

        boolean stocksAreAvailable = currentQnt >= askedQnt;

        if (portfolioDao.UserHaveStocks(tradeDTO.getIsin(),tradeDTO.getUserId()) || !stocksAreAvailable) {
            //E
        }

        portfolioDao.removeQuantity(tradeDTO.getQuantity(), tradeDTO.getIsin(), tradeDTO.getUserId());
    }

    @Override
    public void buyTrade(TradeDTO tradeDTO) {

        if (portfolioDao.UserHaveStocks(tradeDTO.getIsin(), tradeDTO.getUserId())) {
            portfolioDao.addQuantity(tradeDTO.getQuantity(), tradeDTO.getIsin(), tradeDTO.getUserId());
        } else {
            portfolioDao.addToPortfolio(tradeDTO, stockDao.getOpen(tradeDTO.getIsin()));
        }

    }

    @Override
    public void addTrade(TradeDTO tradeDTO) {

        if (tradeDTO.getTypeOfTrade().equalsIgnoreCase("sell")) {
            sellTrade(tradeDTO);
        } else if (tradeDTO.getTypeOfTrade().equalsIgnoreCase("buy")) {
            buyTrade(tradeDTO);
        } else {
            //E
        }
    }

}
