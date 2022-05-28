package com.example.retrywithjedis.domain.payment;

import redis.clients.jedis.exceptions.JedisDataException;

import java.util.UUID;

public interface PaymentCache {
    void storePayment(UUID paymentUUID, String status);

    PaymentStatus retrievePayment(UUID paymentUUID);

    void flushPaymentCache(UUID paymentUUID);

    void verifyIdempotency(UUID UUID) throws JedisDataException;

    void clearAll();

}
