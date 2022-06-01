package io.paymentsAPI.kernel;

public class PaymentFailedException extends RuntimeException {

    private final Long paymentId;

    public PaymentFailedException(String message, Long paymentId) {
        super(message);
        this.paymentId = paymentId;
    }

    public static PaymentFailedException withId(Long paymentId) {
        return new PaymentFailedException(String.format("Payment failed with payment ID %s.", paymentId), paymentId);
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
