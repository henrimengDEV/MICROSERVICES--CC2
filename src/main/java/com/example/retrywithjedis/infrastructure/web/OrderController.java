package com.example.retrywithjedis.infrastructure.web;

import com.example.retrywithjedis.domain.order.Order;
import com.example.retrywithjedis.infrastructure.repository.jpa.JpaOrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
public final class OrderController {

    private final JpaOrderRepository jpaOrderRepository;

    public OrderController(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @PostMapping
    public ResponseEntity<Order> create() {
        Order order = new Order(UUID.randomUUID());
        Order save = jpaOrderRepository.save(order);
        return ResponseEntity.ok(save);
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> read() {
        return ResponseEntity.ok(jpaOrderRepository.findAll());
    }
}
