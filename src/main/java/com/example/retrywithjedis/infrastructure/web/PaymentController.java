package com.example.retrywithjedis.infrastructure.web;

import com.example.retrywithjedis.domain.payment.CreaditCardInfo;
import com.example.retrywithjedis.domain.payment.Payment;
import com.example.retrywithjedis.domain.order.Order;
import com.example.retrywithjedis.domain.payment.BuyerInfo;
import com.example.retrywithjedis.domain.payment.PaymentTransactional;
import com.example.retrywithjedis.infrastructure.repository.jpa.PaymentRepository;
import com.example.retrywithjedis.infrastructure.repository.jpa.PaymentTransactionalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/payments")
public final class PaymentController {

    private final PaymentRepository jpaPaymentRepository;
    private final PaymentTransactionalRepository paymentTransactionalRepository;

    public PaymentController(PaymentRepository jpaPaymentRepository, PaymentTransactionalRepository paymentTransactionalRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
        this.paymentTransactionalRepository = paymentTransactionalRepository;
    }

    @PostMapping("{transactionId}")
    public ResponseEntity<?> create(@PathVariable UUID transactionId) {
        Optional<PaymentTransactional> transaction = paymentTransactionalRepository.findById(transactionId);
        if(transaction.isPresent()){
            return ResponseEntity.ok(transaction.get().getMessage());
        }
        Payment payment = new Payment(
            UUID.randomUUID(),
            new BuyerInfo(
                "Henri",
                "Meng",
                "0102030405",
                "dans ton cul",
                "12345"
            ),
            "checkoutId",
            new CreaditCardInfo(
                "0102 0304 0506 0708",
                "05/23",
                "123"
            ),
            new ArrayList<Order>()
        );
        Payment save = jpaPaymentRepository.save(payment);
        paymentTransactionalRepository.save(
                new PaymentTransactional(transactionId, "Payement already passed")
        );
        return ResponseEntity.ok(save);
    }

    @GetMapping
    public ResponseEntity<?> read() {
        return ResponseEntity.ok(jpaPaymentRepository.findAll());
    }
}
