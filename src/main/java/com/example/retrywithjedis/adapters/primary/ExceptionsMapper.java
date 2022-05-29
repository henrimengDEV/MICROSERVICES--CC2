package com.example.retrywithjedis.adapters.primary;

import com.example.retrywithjedis.kernel.PaymentAlreadyInProcessException;
import com.example.retrywithjedis.kernel.PaymentFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import redis.clients.jedis.exceptions.JedisDataException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ControllerAdvice
public class ExceptionsMapper {
    @ExceptionHandler(JedisDataException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<?> handleJedisDataException(PaymentAlreadyInProcessException exception) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .header("Location",
                        linkTo(methodOn(PaymentController.class).getPaymentByTransactionUUID(exception.getPaymentTransactionId().toString())).toUri().toString())
                .body(exception.getMessage());
    }

    @ExceptionHandler(PaymentFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlePaymentFailedException(PaymentFailedException exception) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .header("Location",
                        linkTo(methodOn(PaymentController.class).getPaymentById(exception.getPaymentId())).toUri().toString())
                .body(exception.getMessage());
    }
}



