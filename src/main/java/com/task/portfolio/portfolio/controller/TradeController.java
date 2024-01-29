package com.task.portfolio.portfolio.controller;

import com.task.portfolio.portfolio.dto.TradeDTO;
import com.task.portfolio.portfolio.message.ResponseMessage;
import com.task.portfolio.portfolio.service.TradeServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trades")
public class TradeController {

    private final TradeServices tradeServices;

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addTrade(@Valid @RequestBody TradeDTO tradeDTO) {

        System.out.println(tradeDTO);
        try {
            tradeServices.addTrade(tradeDTO);
            String message = "trade recorded successfully";
            return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.OK);
        } catch (Exception e) {
            String message = e.getMessage();
            return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.BAD_REQUEST);
        }

    }


}
