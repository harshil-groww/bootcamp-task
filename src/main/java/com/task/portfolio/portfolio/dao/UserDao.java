package com.task.portfolio.portfolio.dao;

import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDao {

    private UserRepository userRepository;

//    public UserDao(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public List<Portfolio> getPortfolio(Long userId) {
        Optional<User> user = userRepository.getById(userId);

        if (user.isEmpty()) {
//            throw exception
        }


        return user.get().getPortfolios();
    }


}
