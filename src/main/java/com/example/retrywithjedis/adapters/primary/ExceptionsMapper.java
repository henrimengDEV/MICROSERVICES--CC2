package com.example.retrywithjedis.adapters.primary;

import com.example.retrywithjedis.kernel.PaymentFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import redis.clients.jedis.exceptions.JedisDataException;

@ControllerAdvice
public class ExceptionsMapper {
    @ExceptionHandler(JedisDataException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public String handleJedisDataException(JedisDataException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(PaymentFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handlePaymentFailedException(PaymentFailedException exception) {
        return exception.getMessage();
    }
}



