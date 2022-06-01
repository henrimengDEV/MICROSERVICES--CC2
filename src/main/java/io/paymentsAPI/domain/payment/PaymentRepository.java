package io.paymentsAPI.domain.payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    PaymentId save(Payment Payment);

    Payment findById(PaymentId PaymentId);

    Optional<Payment> findByUUID(UUID uuid);

    List<Payment> findAll();

    void delete(PaymentId PaymentId);
}