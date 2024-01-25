package com.task.portfolio.portfolio.dto;

import com.task.portfolio.portfolio.enums.TradeType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class TradeDTO {

    @NotBlank (message = "userId can not be empty")
    private Long userId;

    @NotBlank (message = "isin can not be empty")
    private String isin;

    private String typeOfTrade;

    @Positive (message = "quantity can not be non positive")
    private Integer quantity;

}
