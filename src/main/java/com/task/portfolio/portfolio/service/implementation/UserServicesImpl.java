package com.task.portfolio.portfolio.service.implementation;

import com.task.portfolio.portfolio.ResponseDTO.Holdings;
import com.task.portfolio.portfolio.ResponseDTO.PortfolioResponse;
import com.task.portfolio.portfolio.dao.PortfolioDao;
import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.dao.UserDao;
import com.task.portfolio.portfolio.dto.UserDTO;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.service.UserServices;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final PortfolioDao portfolioDao;
    private final StockDao stockDao;
    private final UserDao userDao;

    @Override
    public PortfolioResponse getPortfolio(Long userId) {

        PortfolioResponse portfolioResponse = new PortfolioResponse();

        List<Holdings> holdings = new ArrayList<Holdings>();
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
            holding.setGainLoss(round((stockDao.getOpen(portfolio.getIsin())-portfolio.getBuyPrice())*portfolio.getQuantity()));

            holdings.add(holding);

            totalHolding += stockDao.getOpen(portfolio.getIsin())*portfolio.getQuantity();
            totalBuyPrice += portfolio.getBuyPrice()*portfolio.getQuantity();
        }

        totalGainLoss = totalHolding - totalBuyPrice;
        if(totalBuyPrice!=0) {
            percentageGainLoss = totalGainLoss / totalBuyPrice;
        }
        percentageGainLoss *= 100;

        portfolioResponse.setHoldings(holdings);

        portfolioResponse.setTotalHoldings(round(totalHolding));
        portfolioResponse.setTotalBuyPrice(round(totalBuyPrice));

        portfolioResponse.setTotalProfitLoss(round(totalGainLoss));
        portfolioResponse.setTotalProfitPercentage(round(percentageGainLoss));

        return portfolioResponse;
    }

    public User addUser(UserDTO userDTO)
    {
        User user = new User();
        user.setEmailId(userDTO.getEmailId());
        user.setPhoneNo(userDTO.getPhoneNo());
        user.setPan(userDTO.getPan());
        user.setName(userDTO.getName());

        return userDao.addUser(user);
    }

    private Double round(Double val){
        return ((Math.round(val*100))/100.00);
    }
}
