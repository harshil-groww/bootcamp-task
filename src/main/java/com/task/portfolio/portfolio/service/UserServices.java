package com.task.portfolio.portfolio.service;

import com.task.portfolio.portfolio.ResponseDTO.PortfolioResponse;
import com.task.portfolio.portfolio.dto.UserDTO;
import com.task.portfolio.portfolio.entity.sql.User;

public interface UserServices {

    public PortfolioResponse getPortfolio(Long userId);
    public User addUser(UserDTO userDTO);
}
