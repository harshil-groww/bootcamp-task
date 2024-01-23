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


    public User getUser(String userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
//            throw exception
        }

        return user.get();
    }
    public List<Portfolio> getPortfolios(String userId) {

        User user = getUser(userId);

        return user.getPortfolios();
    }
}
