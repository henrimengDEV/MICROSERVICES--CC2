package io.swagger.application;

public class RetrievePaymentRequest {

    private final int paymentId;

    private RetrievePaymentRequest(int paymentId) {
        this.paymentId = paymentId;
    }

    public static RetrievePaymentRequest of(int paymentId) {
        return new RetrievePaymentRequest(paymentId);
    }

    public int getPaymentId() {
        return paymentId;
    }
}
