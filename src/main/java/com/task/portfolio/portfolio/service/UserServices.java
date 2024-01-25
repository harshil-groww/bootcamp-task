package com.task.portfolio.portfolio.service;

import com.task.portfolio.portfolio.ResponseDTO.PortfolioResponse;

public interface UserServices {

    public PortfolioResponse getPortfolio(Long userId);
}
