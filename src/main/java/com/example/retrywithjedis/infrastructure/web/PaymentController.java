package com.example.retrywithjedis.infrastructure.web;

import com.example.retrywithjedis.domain.payment.CreaditCardInfo;
import com.example.retrywithjedis.domain.payment.Payment;
import com.example.retrywithjedis.domain.order.Order;
import com.example.retrywithjedis.domain.payment.BuyerInfo;
import com.example.retrywithjedis.infrastructure.repository.jpa.JpaPaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/v1/payments")
public final class PaymentController {

    private final JpaPaymentRepository jpaPaymentRepository;

    public PaymentController(JpaPaymentRepository jpaPaymentRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
    }

    @PostMapping
    public ResponseEntity<?> create() {
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
        return ResponseEntity.ok(save);
    }

    @GetMapping
    public ResponseEntity<?> read() {
        return ResponseEntity.ok(jpaPaymentRepository.findAll());
    }
}
