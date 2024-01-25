package com.task.portfolio.portfolio.service.implementation;

import com.task.portfolio.portfolio.ResponseDTO.Holdings;
import com.task.portfolio.portfolio.ResponseDTO.PortfolioResponse;
import com.task.portfolio.portfolio.dao.PortfolioDao;
import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final PortfolioDao portfolioDao;
    private final StockDao stockDao;

    @Override
    public PortfolioResponse getPortfolio(Long userId) {

        PortfolioResponse portfolioResponse = new PortfolioResponse();
//        portfolioResponse.setHoldings(null);

        List<Holdings> holdings = null;
        List<Portfolio> portfolios = portfolioDao.getAllPortfolios(userId);

        double totalGainLoss = 0;
        double percentageGainLoss = 0;
        double totalHolding = 0;
        double totalBuyPrice = 0;


        for (Portfolio portfolio : portfolios) {
            Holdings holding = new Holdings();

            holding.setName((stockDao.getStock(portfolio.getIsin())).getName());
            holding.setId(portfolio.getIsin());
            holding.setQuantity(portfolio.getQuantity());
            holding.setBuyPrice(portfolio.getBuyPrice());
            holding.setCurrentPrice(stockDao.getOpen(portfolio.getIsin()));
            holding.setGainLoss(stockDao.getOpen(portfolio.getIsin())-portfolio.getBuyPrice());

            holdings.add(holding);

            totalHolding += stockDao.getOpen(portfolio.getIsin())*portfolio.getQuantity();
            totalBuyPrice += portfolio.getBuyPrice()*portfolio.getQuantity();
        }

        totalGainLoss = totalHolding - totalBuyPrice;
        percentageGainLoss = totalGainLoss/totalBuyPrice;
        percentageGainLoss *= 100;

        portfolioResponse.setHoldings(holdings);

        portfolioResponse.setTotalHoldings(totalHolding);
        portfolioResponse.setTotalBuyPrice(totalBuyPrice);

        portfolioResponse.setTotalProfitLoss(totalGainLoss);
        portfolioResponse.setTotalProfitPercentage(percentageGainLoss);

        return portfolioResponse;
    }
}
