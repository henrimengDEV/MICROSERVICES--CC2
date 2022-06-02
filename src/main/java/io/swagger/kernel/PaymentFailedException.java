package io.swagger.kernel;

public class PaymentFailedException extends RuntimeException {

    private final int paymentId;

    public PaymentFailedException(String message, int paymentId) {
        super(message);
        this.paymentId = paymentId;
    }

    public static PaymentFailedException withId(int paymentId) {
        return new PaymentFailedException(String.format("Payment failed with payment ID %s.", paymentId), paymentId);
    }

    public int getPaymentId() {
        return paymentId;
    }
}
