package io.swagger.domain.payment;

import io.swagger.domain.Order;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public final class Payment implements Serializable {

    private final UUID uuid;
    private PaymentId paymentId;
    private final BuyerInfo buyerInfo;
    private final CreaditCardInfo creditCardInfo;
    private final List<Order> paymentOrders;
    private PaymentStatus status;

    private Payment(UUID uuid, PaymentId id, BuyerInfo buyerInfo, CreaditCardInfo creditCardInfo, List<Order> paymentOrders, PaymentStatus status) {
        this.uuid = uuid;
        this.paymentId = id;
        this.buyerInfo = buyerInfo;
        this.creditCardInfo = creditCardInfo;
        this.paymentOrders = paymentOrders;
        this.status = status;
    }

    public static Payment of(UUID uuid, PaymentId paymentId, BuyerInfo buyerInfo, CreaditCardInfo creditCardInfo, List<Order> paymentOrders, PaymentStatus paymentStatus) {
        return new Payment(uuid, paymentId, buyerInfo, creditCardInfo, paymentOrders, paymentStatus);
    }

    public UUID getUuid() {
        return uuid;
    }

    public PaymentId getPaymentId() {
        return paymentId;
    }

    public BuyerInfo getBuyerInfo() {
        return buyerInfo;
    }

    public CreaditCardInfo getCreditCardInfo() {
        return creditCardInfo;
    }

    public List<Order> getPaymentOrders() {
        return paymentOrders;
    }

    public void setPaymentId(int id) {
        this.paymentId = PaymentId.of(id);
    }

    public PaymentStatus getStatus() {
        return this.status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}