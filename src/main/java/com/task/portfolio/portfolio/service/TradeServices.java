package com.task.portfolio.portfolio.service;

import com.task.portfolio.portfolio.dto.TradeDTO;

public interface TradeServices {

    public void sellTrade(TradeDTO tradeDTO);
    public void buyTrade(TradeDTO tradeDTO);
    public void addTrade(TradeDTO tradeDTO);
}
