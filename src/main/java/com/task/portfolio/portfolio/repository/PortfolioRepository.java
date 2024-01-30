package com.task.portfolio.portfolio.repository;

import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.entity.sql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, String> {
    Optional<Portfolio> findByIsinAndUser(String isin, User user);

    List<Portfolio> findPortfoliosByUserAndIsDeleted(User user, Boolean isDeleted);
}
