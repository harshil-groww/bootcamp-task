package com.task.portfolio.portfolio.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Holdings {

    private String name;
    private String id;
    private Integer quantity;
    private Double buyPrice;
    private Double currentPrice;
    private Double gainLoss;
}
