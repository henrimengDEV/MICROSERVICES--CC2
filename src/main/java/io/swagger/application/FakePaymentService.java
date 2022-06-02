package io.swagger.application;

import io.swagger.domain.Order;
import io.swagger.domain.payment.*;
import io.swagger.kernel.PaymentFailedException;

import java.util.*;
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
        UUID transactionUUID = UUID.fromString(paymentRequest.getTransactionUUID());

        //Check in cache if payment exist
        paymentCache.verifyTransactionIdempotency(transactionUUID);

        //Check in persistence (source of truth) if the payment is already done
        Optional<Payment> existingPayment = paymentRepository.findByUUID(transactionUUID);
        if (existingPayment.isPresent() && !existingPayment.get().getStatus().equals(PaymentStatus.FAILED))
            throw PaymentFailedException.withId(existingPayment.get().getPaymentId().getValue());

        List<Order> orders =  new ArrayList<>();
        paymentRequest.getPaymentOrders().forEach(paymentOrder -> {
            Order order = Order.of(paymentOrder.id, paymentOrder.name);
            orders.add(order);
        });
        Payment payment = Payment.of(
                UUID.fromString(paymentRequest.getTransactionUUID()),
                PaymentId.of(-1),
                new BuyerInfo(
                        paymentRequest.getBuyerInfo().getFirstname(),
                        paymentRequest.getBuyerInfo().getLastname(),
                        paymentRequest.getBuyerInfo().getPhone(),
                        paymentRequest.getBuyerInfo().getAddress(),
                        paymentRequest.getBuyerInfo().getZipcode()),
                new CreaditCardInfo(
                        paymentRequest.getCreditCardInfo().getCardNumber(),
                        paymentRequest.getCreditCardInfo().getExpirationDate(),
                        paymentRequest.getCreditCardInfo().getCardVerificationValue()),
                orders,
                PaymentStatus.IN_PROGRESS
        );

        //Store in cache this transaction
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

        PaymentId paymentId = paymentRepository.save(payment);  //Saving to persistence(source of truth)

        if (payment.getStatus().equals(PaymentStatus.FAILED)) {
            //Delete this transaction in cache
            paymentCache.flushTransactionCache(payment.getUuid());
            throw new PaymentFailedException("Can't connect to Stripe API", payment.getPaymentId().getValue());
        } else {
            //Update this transaction in cache
            paymentCache.storeTransaction(payment.getUuid(), payment.getStatus().getValue());
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
