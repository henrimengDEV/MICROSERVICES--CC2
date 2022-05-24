package com.example.retrywithjedis.infrastructure.web;

import com.example.retrywithjedis.domain.order.Order;
import com.example.retrywithjedis.domain.payment.BuyerInfo;
import com.example.retrywithjedis.domain.payment.CreaditCardInfo;
import com.example.retrywithjedis.domain.payment.Payment;
import com.example.retrywithjedis.domain.payment.PaymentTransactional;
import com.example.retrywithjedis.infrastructure.repository.jpa.PaymentRepository;
import com.example.retrywithjedis.infrastructure.repository.jpa.PaymentTransactionalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/payment-transactions")
public final class PaymentTransactionalController {


    private final PaymentTransactionalRepository paymentTransactionalRepository;

    public PaymentTransactionalController(PaymentTransactionalRepository paymentTransactionalRepository) {

        this.paymentTransactionalRepository = paymentTransactionalRepository;
    }


    @GetMapping
    public ResponseEntity<?> read() {
        return ResponseEntity.ok(paymentTransactionalRepository.findAll());
    }
}
