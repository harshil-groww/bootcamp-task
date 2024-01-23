package com.task.portfolio.portfolio.ResponseDTO;

import lombok.Data;

@Data
public class Holdings {

    private String name;
    private String id;
    private Integer quantity;
    private Double buyPrice;
    private Double currentPrice;
    private Double gainLoss;
}
