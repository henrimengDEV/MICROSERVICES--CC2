package io.paymentsAPI.adapters.primary.filter;

import io.paymentsAPI.adapters.primary.PaymentController;
import io.paymentsAPI.kernel.PaymentAlreadyInProcessException;
import io.paymentsAPI.kernel.PaymentFailedException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ControllerAdvice
public class ExceptionsMapper {
    @ExceptionHandler(PaymentAlreadyInProcessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseEntity<?> handleJedisDataException(PaymentAlreadyInProcessException exception) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .header("Location",
                        WebMvcLinkBuilder.linkTo(methodOn(PaymentController.class).getPaymentStatusByTransactionUUID(exception.getPaymentTransactionId().toString())).toUri().toString())
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



