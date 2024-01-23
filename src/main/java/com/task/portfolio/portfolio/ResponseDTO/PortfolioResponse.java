package com.task.portfolio.portfolio.ResponseDTO;

import lombok.Data;

import java.util.List;

@Data
public class PortfolioResponse {

    private List<Holdings> holdings;
    private Double totalHoldings;
    private Double totalBuyPrice;
    private Double totalProfitLoss;
    private Double totalProfitPercentage;
}
