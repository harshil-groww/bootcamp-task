package com.task.portfolio.portfolio.dao;

import com.task.portfolio.portfolio.Exception.NotFoundException;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
//import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;


    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        Optional<User> userDetails = userRepository.findById(userId);

        if (userDetails.isEmpty()) {
            throw new NotFoundException("user not found");
        }

        return userDetails.get();
    }

    public List<Portfolio> getPortfolios(Long userId) {

        User user = getUser(userId);

        return user.getPortfolios();
    }
}
