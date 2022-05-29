package com.example.retrywithjedis.adapters.secondary.database;

import com.example.retrywithjedis.domain.payment.Payment;
import com.example.retrywithjedis.domain.payment.PaymentId;
import com.example.retrywithjedis.domain.payment.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {

    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<PaymentId, Payment> data = new ConcurrentHashMap<>();

    @Override
    public PaymentId save(Payment payment) {
        payment.setPaymentId(count.incrementAndGet());
        data.put(payment.getPaymentId(), payment);
        return payment.getPaymentId();
    }

    @Override
    public Payment findById(PaymentId PaymentId) {
        final Payment Payment = data.get(PaymentId);
        if (Payment == null) {
            throw new NoSuchElementException("No entity Payment found with id : " + PaymentId.getValue());
        }
        return Payment;
    }

    @Override
    public Optional<Payment> findByUUID(UUID uuid) {
        return data.values().stream()
                .filter(payment -> payment.getUuid().equals(uuid)).findFirst();
    }

    @Override
    public List<Payment> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public void delete(PaymentId PaymentId) {
        data.remove(PaymentId);
    }
}
