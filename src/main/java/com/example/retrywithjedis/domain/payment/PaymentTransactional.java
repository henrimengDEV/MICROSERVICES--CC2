package com.example.retrywithjedis.domain.payment;



import java.util.UUID;

//@RedisHash("PaymentTransactional")
public final class PaymentTransactional {

    private final UUID id;
    private final String message;

    public PaymentTransactional(UUID id, String message) {
        this.id = id;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
