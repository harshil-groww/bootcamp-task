package com.task.portfolio.portfolio.dao;

import com.task.portfolio.portfolio.dto.TradeDTO;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.repository.PortfolioRepository;
import com.task.portfolio.portfolio.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PortfolioDao {

    private PortfolioRepository portfolioRepository;
    private UserRepository userRepository;
    private UserDao userDao;


    public List<Portfolio> getAllPortfolios(String userId)
    {
        User user = userDao.getUser(userId);
        return user.getPortfolios();
    }

    public void addToPortfolio(String userId, String isin, Integer qnt, Double price) {

        User user = userDao.getUser(userId);

        Portfolio portfolio = new Portfolio();
        portfolio.setIsin(isin);
        portfolio.setQuantity(qnt);
        portfolio.setBuyPrice(price);
        portfolio.setUser(user);

        portfolioRepository.save(portfolio);
    }
    public void addToPortfolio(TradeDTO tradeDTO, Double price){
        addToPortfolio(tradeDTO.getUserId(), tradeDTO.getIsin(), tradeDTO.getQuantity(), price);
    }

    public void addQuantity(Integer quantityToAdd, String isin, String userId){
        User user = userDao.getUser(userId);
        Optional<Portfolio> portfolio = portfolioRepository.findByIsinAndUser(isin, user);

        if(portfolio.isEmpty())
        {
            //E
        }

        portfolio.get().setQuantity(portfolio.get().getQuantity()+quantityToAdd);
        portfolioRepository.save(portfolio.get());
    }

    public boolean doesUserHaveStocks(String isin, String userId)
    {
        User user = userDao.getUser(userId);

        Optional<Portfolio> portfolio= portfolioRepository.findByIsinAndUser(isin, user);

        return portfolio.isPresent();
    }

}
