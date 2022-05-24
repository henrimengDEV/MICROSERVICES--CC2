package com.example.retrywithjedis.infrastructure.repository.jpa;

import com.example.retrywithjedis.domain.payment.PaymentTransactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentTransactionalRepository extends CrudRepository<PaymentTransactional, UUID> {
}
