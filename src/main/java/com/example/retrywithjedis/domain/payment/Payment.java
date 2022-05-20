package com.example.retrywithjedis.domain.payment;

import com.example.retrywithjedis.domain.order.Order;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@RedisHash("Payment")
public final class Payment implements Serializable {

    private final UUID id;
    private final BuyerInfo buyerInfo;
    private final String checkoutId;
    private final CreaditCardInfo creditCardInfo;
    private final List<Order> paymentOrders;

    public Payment(UUID id, BuyerInfo buyerInfo, String checkoutId, CreaditCardInfo creditCardInfo, List<Order> paymentOrders) {
        this.id = id;
        this.buyerInfo = buyerInfo;
        this.checkoutId = checkoutId;
        this.creditCardInfo = creditCardInfo;
        this.paymentOrders = paymentOrders;
    }

    public UUID getId() {
        return id;
    }

    public BuyerInfo getBuyerInfo() {
        return buyerInfo;
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public CreaditCardInfo getCreditCardInfo() {
        return creditCardInfo;
    }

    public List<Order> getPaymentOrders() {
        return paymentOrders;
    }
}
