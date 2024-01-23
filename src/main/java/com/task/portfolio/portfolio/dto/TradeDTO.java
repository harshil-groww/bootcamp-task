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
@Builder
public class TradeDTO {

    private String id;

    @NotBlank (message = "userId can not be empty")
    private String userId;

    @NotBlank (message = "isin can not be empty")
    private String isin;

    @PastOrPresent (message = "Trade date can not be in future")
    private Date txnDate;

    private TradeType typeOfTrade;

    @Positive (message = "quantity can not be non positive")
    private Integer quantity;

}
