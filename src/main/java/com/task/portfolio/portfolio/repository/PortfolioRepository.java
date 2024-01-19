package com.task.portfolio.portfolio.repository;

import com.task.portfolio.portfolio.entity.sql.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,String> {
}
