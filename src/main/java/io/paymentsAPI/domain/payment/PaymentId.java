package io.paymentsAPI.domain.payment;

import io.paymentsAPI.kernel.ValueObjectId;

public class PaymentId implements ValueObjectId {

    private final Long value;

    private PaymentId(Long value) {
        this.value = value;
    }

    public static PaymentId of(Long value) {
        return new PaymentId(value);
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "PaymentId{" +
                "value=" + value +
                '}';
    }
}
