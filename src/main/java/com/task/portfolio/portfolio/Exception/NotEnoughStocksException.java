package com.task.portfolio.portfolio.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NotEnoughStocksException extends RuntimeException {
    public NotEnoughStocksException(String message) {
        super(message);
    }
}
