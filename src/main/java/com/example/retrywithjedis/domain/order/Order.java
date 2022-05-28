package com.example.retrywithjedis.domain.order;



import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

//@RedisHash("Order")
public final class Order implements Serializable {

    private final OrderId orderId;

    public Order(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId.equals(order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
