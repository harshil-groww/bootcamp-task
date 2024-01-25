package com.task.portfolio.portfolio.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
public class StockDTO {

    private String isin;
    private String name;

    private Double open;
    private Double high;
    private Double low;
    private Double close;
}
