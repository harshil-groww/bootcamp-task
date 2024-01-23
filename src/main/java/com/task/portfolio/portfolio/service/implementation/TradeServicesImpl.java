package com.task.portfolio.portfolio.service.implementation;

import com.task.portfolio.portfolio.dao.PortfolioDao;
import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.dao.UserDao;
import com.task.portfolio.portfolio.dto.TradeDTO;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.enums.TradeType;
import com.task.portfolio.portfolio.repository.PortfolioRepository;
import com.task.portfolio.portfolio.repository.StockRepository;
import com.task.portfolio.portfolio.repository.TradeRepository;
import com.task.portfolio.portfolio.repository.UserRepository;
import com.task.portfolio.portfolio.service.TradeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradeServicesImpl implements TradeServices {

    private final TradeRepository tradeRepository;
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    private final UserDao userDao;
    private final PortfolioDao portfolioDao;
    private final StockDao stockDao;

    @Override
    public void sellTrade(TradeDTO tradeDTO) {

        Optional<User> user = userRepository.findById(tradeDTO.getUserId());
        Optional<Portfolio> portfolio = portfolioRepository.findByIsinAndUser(tradeDTO.getIsin(), user.get());
        if (user.isEmpty() || portfolio.isEmpty()) {
            //E
        }

        Integer currentQnt = portfolio.get().getQuantity();
        Integer askedQnt = tradeDTO.getQuantity();

        boolean stocksAreAvailable = currentQnt >= askedQnt;

        if (!stocksAreAvailable) {
            //E
        }

        Integer qntAfterTrade = currentQnt - askedQnt;

    }

    @Override
    public void buyTrade(TradeDTO tradeDTO) {

        if (portfolioDao.doesUserHaveStocks(tradeDTO.getIsin(), tradeDTO.getUserId())) {
            portfolioDao.addQuantity(tradeDTO.getQuantity(), tradeDTO.getIsin(), tradeDTO.getUserId());
        } else {
            portfolioDao.addToPortfolio(tradeDTO, stockDao.getOpen(tradeDTO.getIsin()));
        }

    }

    @Override
    public void addTrade(TradeDTO tradeDTO) {

        if (tradeDTO.getTypeOfTrade() == TradeType.SELL) {
            sellTrade(tradeDTO);
        } else if (tradeDTO.getTypeOfTrade() == TradeType.BUY) {
            buyTrade(tradeDTO);
        } else {
            //E
        }
    }

}
