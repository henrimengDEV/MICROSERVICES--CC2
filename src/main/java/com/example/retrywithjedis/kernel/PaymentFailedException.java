package com.example.retrywithjedis.kernel;

import java.util.UUID;

public class PaymentFailedException extends RuntimeException{

    public PaymentFailedException(String message) {
        super(message);
    }

    public static PaymentFailedException withId(UUID transactionId) {
        return new PaymentFailedException(String.format("Payment failed with transaction ID %s.", transactionId.toString()));
    }
}
