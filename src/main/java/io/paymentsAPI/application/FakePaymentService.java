package io.paymentsAPI.application;

import io.paymentsAPI.domain.payment.*;
import io.paymentsAPI.kernel.PaymentFailedException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class FakePaymentService implements PaymentService {
    private final PaymentCache paymentCache;
    private final PaymentRepository paymentRepository;

    private final AtomicInteger counter = new AtomicInteger();

    public FakePaymentService(PaymentCache paymentCache, PaymentRepository paymentRepository) {
        this.paymentCache = paymentCache;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentId pay(CreatePaymentCommand paymentRequest) {

        UUID transactionUUID = UUID.fromString(paymentRequest.getUuid());
        //Check in cache if payment exist
        paymentCache.verifyTransactionIdempotency(transactionUUID);

        //Check in persistence (source of truth) if the payment is already processed
        Optional<Payment> existingPayment = paymentRepository.findByUUID(transactionUUID);
        if (existingPayment.isPresent() && !existingPayment.get().getStatus().equals(PaymentStatus.FAILED))
            throw PaymentFailedException.withId(existingPayment.get().getPaymentId().getValue());

        //fake paymentRequest data
        Payment payment = Payment.of(UUID.fromString(paymentRequest.getUuid()), new BuyerInfo("Henri", "Meng", "0102030405", "62 rue de redis", "13375"), "checkoutId", new CreaditCardInfo("0102 0304 0506 0708", "05/23", "123"), new ArrayList<String>(), PaymentStatus.IN_PROGRESS);

        paymentCache.storeTransaction(transactionUUID, payment.getStatus().getValue());

        //Fake connection to payment plateform of 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Fake connection failure
        if (counter.incrementAndGet() % 3 == 0)
            payment.setStatus(PaymentStatus.SUCCESS);
        else
            payment.setStatus(PaymentStatus.FAILED);

        // End of fake proceed to payment

        PaymentId paymentId = paymentRepository.save(payment);          //Saving to persistence(source of truth)

        if (payment.getStatus().equals(PaymentStatus.FAILED))
            paymentCache.flushTransactionCache(payment.getUuid());
        else            //Update cache
            paymentCache.storeTransaction(payment.getUuid(), payment.getStatus().getValue());

        if (payment.getStatus().equals(PaymentStatus.FAILED)) {
            throw new PaymentFailedException("Can't connect to Stripe API", payment.getPaymentId().getValue());
        }
        return paymentId;
    }

    @Override
    public Payment retrievePayment(RetrievePaymentRequest retrievePaymentRequest) {
        PaymentId paymentId = PaymentId.of(retrievePaymentRequest.getPaymentId());
        Payment payment;

        payment = paymentCache.retrievePayment(paymentId);  //Check in cache if payment exist

        if (Objects.isNull(payment))
            payment = paymentRepository.findById(paymentId);    //if not retrieve payment from database

        return payment;
    }

    @Override
    public String retrieveTransaction(RetrieveTransactionRequest retrieveTransactionRequest) {
        UUID transactionUUID = UUID.fromString(retrieveTransactionRequest.getUUID());
        return paymentCache.retrieveTransaction(transactionUUID);
    }
}
