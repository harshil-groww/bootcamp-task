package com.task.portfolio.portfolio.controller;

import com.task.portfolio.portfolio.entity.sql.Stock;
import com.task.portfolio.portfolio.message.ResponseMessage;
import com.task.portfolio.portfolio.service.StockServices;
import com.task.portfolio.portfolio.service.implementation.StockServicesImpl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stocks")
public class StockController {

    private final StockServices stockServices;

    @GetMapping
    public ResponseEntity<?> getStock(@RequestParam String isin) {
        try {
            return new ResponseEntity<>(stockServices.getStock(isin), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseMessage> updateStocks(@RequestParam("file") MultipartFile file) {

        if (stockServices.isCsv(file)) {
            try {
                stockServices.updateStock(file);

                String message = "Stocks updated successfully";
                ResponseMessage responseMessage = new ResponseMessage(message);

                return new ResponseEntity<>(responseMessage, HttpStatus.OK);
            } catch (Exception e) {
                String message = e.getMessage();
                ResponseMessage responseMessage = new ResponseMessage(message);
                return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        String message = "Please upload CSV file";
        return new ResponseEntity<>(new ResponseMessage(message), HttpStatus.BAD_REQUEST);
    }

}
