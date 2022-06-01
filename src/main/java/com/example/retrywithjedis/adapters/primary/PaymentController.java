package com.example.retrywithjedis.adapters.primary;

import com.example.retrywithjedis.domain.payment.*;
import com.example.retrywithjedis.kernel.PaymentFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    private final PaymentCache paymentCache;

    public PaymentController(PaymentRepository paymentRepository, PaymentCache paymentCache) {
        this.paymentRepository = paymentRepository;
        this.paymentCache = paymentCache;
    }

    @PostMapping("{transactionId}")
    public ResponseEntity<Void> pay(@PathVariable String transactionId) {
        UUID transactionUUID = UUID.fromString(transactionId);

        //Check in cache if payment exist
        paymentCache.verifyTransactionIdempotency(transactionUUID);

        //Check in persistence (source of truth) if the payment is already processed
        Optional<Payment> existingPayment = paymentRepository.findByUUID(transactionUUID);
        if (existingPayment.isPresent())
            throw PaymentFailedException.withId(existingPayment.get().getPaymentId().getValue());

        //fake paymentRequest data
        Payment payment = Payment.of(UUID.fromString(transactionId), new BuyerInfo("Henri", "Meng", "0102030405", "62 rue de redis", "13375"), "checkoutId", new CreaditCardInfo("0102 0304 0506 0708", "05/23", "123"), new ArrayList<String>(), PaymentStatus.IN_PROGRESS);

        paymentCache.storeTransaction(transactionUUID, payment.getStatus().getValue());

        //Fake proceed to payment of 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        payment.setStatus(PaymentStatus.SUCCESS);
        // End of fake proceed to payment

        //Saving to persistence(source of truth)
        PaymentId paymentId = paymentRepository.save(payment);

        //Update cache
        paymentCache.storeTransaction(payment.getUuid(), payment.getStatus().getValue());
    // TODO store payment in cache

        return ResponseEntity.created(linkTo(methodOn(PaymentController.class).getPaymentById(paymentId.getValue())).toUri()).build();
    }

    @GetMapping("{Id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Integer Id) {
        PaymentId paymentId = PaymentId.of(Id);
        Payment payment;

        payment = paymentCache.retrievePayment(paymentId);  //Check in cache if payment exist

        if (Objects.isNull(payment))
            payment = paymentRepository.findById(paymentId);    //if not retrieve payment from database

        // put it in the cache
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/status/{transactionUUID}")
    public ResponseEntity<?> getPaymentStatusByTransactionUUID(@PathVariable String transactionUUID) {

        String transactionStatus = paymentCache.retrieveTransaction(UUID.fromString(transactionUUID));
        return ResponseEntity.ok(transactionStatus);
    }

    //todo delete and delete in cache
}
