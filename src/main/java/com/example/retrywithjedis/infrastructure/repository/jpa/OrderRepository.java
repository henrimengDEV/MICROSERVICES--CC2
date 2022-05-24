package com.example.retrywithjedis.infrastructure.repository.jpa;

import com.example.retrywithjedis.domain.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {}