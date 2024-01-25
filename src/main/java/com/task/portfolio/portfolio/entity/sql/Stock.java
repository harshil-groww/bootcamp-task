package com.task.portfolio.portfolio.entity.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Stock {

    @Id
    private String id;
    private String name;
    private Double open;
    private Double high;
    private Double low;
    private Double close;

}