package com.task.portfolio.portfolio.controller;

import com.task.portfolio.portfolio.dao.UserDao;
import com.task.portfolio.portfolio.dto.UserDTO;
import com.task.portfolio.portfolio.entity.sql.Portfolio;
import com.task.portfolio.portfolio.entity.sql.User;
import com.task.portfolio.portfolio.message.ResponseMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserDao userDao;
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user){

        try{
            user = userDao.addUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (Exception e){

            return new ResponseEntity<>(new ResponseMessage("Failed to add user"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/portfolio")
    public ResponseEntity<?> getPortfolios(@RequestParam Long userId)
    {
        try{
            List<Portfolio> portfolios = userDao.getPortfolios(userId);
            return new ResponseEntity<>(portfolios, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
