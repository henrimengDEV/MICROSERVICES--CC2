package io.paymentsAPI.application;

public class RetrievePaymentRequest {

    private final Long paymentId;

    private RetrievePaymentRequest(Long paymentId) {
        this.paymentId = paymentId;
    }

    public static RetrievePaymentRequest of(Long paymentId) {
        return new RetrievePaymentRequest(paymentId);
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
