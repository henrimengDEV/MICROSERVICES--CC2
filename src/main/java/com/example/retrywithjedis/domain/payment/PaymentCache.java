package com.example.retrywithjedis.domain.payment;

import com.example.retrywithjedis.kernel.PaymentAlreadyInProcessException;

import java.util.UUID;

public interface PaymentCache {
    void storeTransaction(UUID paymentUUID, String transactionStatus);
    void storePayment(PaymentId paymentUUID, Payment payment);

    String retrieveTransaction(UUID paymentUUID);

    Payment retrievePayment(PaymentId paymentId);

    void flushPaymentCache(UUID paymentUUID);

    void verifyTransactionIdempotency(UUID UUID) throws PaymentAlreadyInProcessException;

    void clearAll();

}
