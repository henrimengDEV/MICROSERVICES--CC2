package com.example.retrywithjedis.infrastructure.repository.jpa;

import com.example.retrywithjedis.domain.payment.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaPaymentRepository extends CrudRepository<Payment, UUID> {}