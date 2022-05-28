package com.example.retrywithjedis.adapters.secondary.persistence;

import com.example.retrywithjedis.domain.order.Order;
import com.example.retrywithjedis.domain.order.OrderId;
import com.example.retrywithjedis.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<OrderId, Order> data = new ConcurrentHashMap<>();

    @Override
    public void save(Order order) {
        data.put(order.getOrderId(), order);
    }

    @Override
    public Order findById(OrderId orderId) {
        final Order order = data.get(orderId);
        if (order == null) {
            throw new NoSuchElementException("No entity Order found with id : " + orderId.getValue());
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public void delete(OrderId orderId) {
        data.remove(orderId);
    }
}
