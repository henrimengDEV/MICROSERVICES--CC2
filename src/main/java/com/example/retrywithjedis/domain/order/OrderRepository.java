package com.example.retrywithjedis.domain.order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);
    Order findById(OrderId orderId);
    List<Order> findAll();
    void delete(OrderId orderId);
}