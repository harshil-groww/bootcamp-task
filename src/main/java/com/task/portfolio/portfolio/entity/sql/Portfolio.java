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

    private Integer quantity;

    private Double buyPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}