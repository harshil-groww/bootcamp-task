package com.task.portfolio.portfolio.entity.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table

public class Portfolio {

    @Id
    private String isin;

    private Integer Quantity;

    private Double BuyPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}