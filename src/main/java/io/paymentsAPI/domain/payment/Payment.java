package io.paymentsAPI.domain.payment;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public final class Payment implements Serializable {

    private final UUID uuid;
    private PaymentId paymentId;
    private final BuyerInfo buyerInfo;
    private final String checkoutId;
    private final CreaditCardInfo creditCardInfo;
    private final List<String> paymentOrders;
    private PaymentStatus status;

    private Payment(UUID uuid, PaymentId id, BuyerInfo buyerInfo, String checkoutId, CreaditCardInfo creditCardInfo, List<String> paymentOrders, PaymentStatus status) {
        this.uuid = uuid;
        this.paymentId = id;
        this.buyerInfo = buyerInfo;
        this.checkoutId = checkoutId;
        this.creditCardInfo = creditCardInfo;
        this.paymentOrders = paymentOrders;
        this.status = status;
    }

    public static Payment of(UUID uuid, BuyerInfo buyerInfo, String checkoutId, CreaditCardInfo creditCardInfo, List<String> paymentOrders, PaymentStatus paymentStatus) {
        return new Payment(uuid, PaymentId.of(-1L), buyerInfo, checkoutId, creditCardInfo, paymentOrders, paymentStatus);
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

    public String getCheckoutId() {
        return checkoutId;
    }

    public CreaditCardInfo getCreditCardInfo() {
        return creditCardInfo;
    }

    public List<String> getPaymentOrders() {
        return paymentOrders;
    }

    public void setPaymentId(Long id) {
        this.paymentId = PaymentId.of(id);
    }

    public PaymentStatus getStatus() {
        return this.status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}