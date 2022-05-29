package com.example.retrywithjedis.kernel;

import redis.clients.jedis.exceptions.JedisDataException;

import java.util.UUID;

public class PaymentAlreadyInProcessException extends JedisDataException {

    private final UUID paymentId;

    public PaymentAlreadyInProcessException(String message, UUID transactionId) {
        super(message);
        this.paymentId = transactionId;
    }

    public static PaymentAlreadyInProcessException withTransactionId(UUID transactionId) {
        return new PaymentAlreadyInProcessException(String.format("Transaction already taken in count with transaction ID %s.", transactionId.toString()), transactionId);
    }

    public UUID getPaymentTransactionId() {
        return paymentId;
    }
}
