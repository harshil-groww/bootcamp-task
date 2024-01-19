package com.task.portfolio.portfolio.repository;

import com.task.portfolio.portfolio.entity.sql.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Stock, Long> {
}
