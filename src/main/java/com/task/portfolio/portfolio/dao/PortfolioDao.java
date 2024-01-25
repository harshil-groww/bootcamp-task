package com.task.portfolio.portfolio.dao;

import com.task.portfolio.portfolio.dto.TradeDTO;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.repository.PortfolioRepository;
import com.task.portfolio.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PortfolioDao {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final UserDao userDao;
    private final StockDao stockDao;


    public List<Portfolio> getAllPortfolios(Long userId)
    {
        User user = userDao.getUser(userId);
        return user.getPortfolios();
    }

    public void addToPortfolio(Long userId, String isin, Integer qnt, Double price) {

        User user = userDao.getUser(userId);

        Portfolio portfolio = new Portfolio();
        portfolio.setIsin(isin);
        portfolio.setQuantity(qnt);
        portfolio.setBuyPrice(price);
        portfolio.setUser(user);

        System.out.println("here");

        portfolioRepository.save(portfolio);
    }
    public void addToPortfolio(TradeDTO tradeDTO, Double price){
        addToPortfolio(tradeDTO.getUserId(), tradeDTO.getIsin(), tradeDTO.getQuantity(), price);
    }

    public Portfolio getPortfolio(String isin, User user)
    {
        Optional<Portfolio> portfolio = portfolioRepository.findByIsinAndUser(isin, user);
        if(portfolio.isEmpty())
        {
            //E
        }

        return portfolio.get();
    }
    public void addQuantity(Integer quantityToAdd, String isin, Long userId){
        User user = userDao.getUser(userId);
        Portfolio portfolio = getPortfolio(isin, user);
        Stock stock = stockDao.getStock(isin);

        Integer currQuantity = portfolio.getQuantity();
        Integer newQuantity = currQuantity+quantityToAdd;

        Double newBuyPrice = (portfolio.getBuyPrice()*currQuantity)+(stock.getOpen()*quantityToAdd);
        newBuyPrice = newBuyPrice/newQuantity;

        portfolio.setQuantity(newQuantity);
        portfolio.setBuyPrice(newBuyPrice);
        portfolioRepository.save(portfolio);
    }

    public void removeQuantity(Integer quantityToRemove, String isin, Long userId){
        User user = userDao.getUser(userId);
        Portfolio portfolio = getPortfolio(isin, user);

        Integer currQuantity = portfolio.getQuantity();
        Integer newQuantity = currQuantity-quantityToRemove;

        portfolio.setQuantity(newQuantity);
        portfolioRepository.save(portfolio);
    }



    public boolean UserHaveStocks(String isin, Long userId)
    {
        User user = userDao.getUser(userId);

        Optional<Portfolio> portfolio= portfolioRepository.findByIsinAndUser(isin, user);

        return portfolio.isPresent();
    }

}
