package com.task.portfolio.portfolio.repository;

import com.task.portfolio.portfolio.entity.sql.TradeNOTNEEDED;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<TradeNOTNEEDED, String> {
}
