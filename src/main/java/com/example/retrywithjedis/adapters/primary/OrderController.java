package com.example.retrywithjedis.adapters.primary;

import com.example.retrywithjedis.domain.order.Order;
import com.example.retrywithjedis.domain.order.OrderId;
import com.example.retrywithjedis.domain.order.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
public final class  OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<?> create() {
        Order order = new Order(new OrderId(1337));
        orderRepository.save(order);
        return ResponseEntity.ok("NEW ORDER SAVED");
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> read() {
        return ResponseEntity.ok(orderRepository.findAll());
    }
}
