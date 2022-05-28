package com.example.retrywithjedis.domain.payment;

import java.util.List;

public interface PaymentRepository {

    PaymentId save(Payment Payment);

    Payment findById(PaymentId PaymentId);

    List<Payment> findAll();

    void delete(PaymentId PaymentId);
}