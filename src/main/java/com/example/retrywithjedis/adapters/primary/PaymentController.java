package com.example.retrywithjedis.adapters.primary;

import com.example.retrywithjedis.domain.order.Order;
import com.example.retrywithjedis.domain.payment.*;
import com.example.retrywithjedis.kernel.PaymentFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/payments")
public final class PaymentController {

    private final PaymentRepository paymentRepository;

    private final PaymentCache paymentCache;

    public PaymentController(PaymentRepository paymentRepository, PaymentCache paymentCache) {
        this.paymentRepository = paymentRepository;
        this.paymentCache = paymentCache;
    }

    @PostMapping("{transactionId}")
    public ResponseEntity<?> create(@PathVariable String transactionId) {
        UUID transactionUUID = UUID.fromString(transactionId);

        paymentCache.verifyIdempotency(transactionUUID);

        Optional<Payment> existingPayment = paymentRepository.findByUUID(transactionUUID);
        if (existingPayment.isPresent())
            throw new PaymentFailedException("Payment already done.");

        //fake paymentRequest data
        Payment payment = Payment.of(
                UUID.fromString(transactionId),
                new BuyerInfo(
                        "Henri",
                        "Meng",
                        "0102030405",
                        "62 rue de redis",
                        "13375"
                ),
                "checkoutId",
                new CreaditCardInfo(
                        "0102 0304 0506 0708",
                        "05/23",
                        "123"
                ),
                new ArrayList<Order>(),
                PaymentStatus.IN_PROGRESS
        );

        paymentCache.storePayment(transactionUUID, payment.getStatus());

        //Fake proceed to payment of 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        payment.setStatus(PaymentStatus.SUCCESS);

        PaymentId paymentId = paymentRepository.save(payment);

        paymentCache.storePayment(payment.getUuid(), payment.getStatus());

        return ResponseEntity.ok(paymentId);
    }

    @GetMapping
    public ResponseEntity<?> read() {
        UUID uuid = UUID.randomUUID();
        Payment payment = Payment.of(
                uuid,
                new BuyerInfo(
                        "Henri",
                        "Meng",
                        "0102030405",
                        "62 rue de redis",
                        "13375"
                ),
                "checkoutId",
                new CreaditCardInfo(
                        "0102 0304 0506 0708",
                        "05/23",
                        "123"
                ),
                new ArrayList<Order>(),
                PaymentStatus.IN_PROGRESS
        );
        paymentCache.storePayment(payment.getUuid(), payment.getStatus());
        var cachedPayment = paymentCache.retrievePayment(uuid);
        return ResponseEntity.ok(cachedPayment);
    }
}
