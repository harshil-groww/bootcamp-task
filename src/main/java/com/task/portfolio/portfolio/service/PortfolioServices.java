package com.task.portfolio.portfolio.service;

import com.task.portfolio.portfolio.ResponseDTO.PortfolioResponse;
import com.task.portfolio.portfolio.dao.PortfolioDao;

public interface PortfolioServices {

    public PortfolioResponse getResponse(String userId);
}
