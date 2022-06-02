package io.swagger.domain.payment;

import io.swagger.kernel.PaymentAlreadyInProcessException;

import java.util.UUID;

public interface PaymentCache {
    void storeTransaction(UUID paymentUUID, String transactionStatus);

    void storePayment(PaymentId paymentUUID, Payment payment);

    String retrieveTransaction(UUID paymentUUID);

    Payment retrievePayment(PaymentId paymentId);

    void flushTransactionCache(UUID paymentUUID);

    void verifyTransactionIdempotency(UUID UUID) throws PaymentAlreadyInProcessException;

    void clearAll();

}
