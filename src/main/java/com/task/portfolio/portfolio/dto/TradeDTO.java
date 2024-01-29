package com.task.portfolio.portfolio.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TradeDTO {

    @NotNull(message = "userId can not be empty")
    private Long userId;

    @NotBlank(message = "isin can not be empty")
    private String isin;

    private String typeOfTrade;

    @Positive(message = "quantity can not be non positive")
    private Integer quantity;

}
