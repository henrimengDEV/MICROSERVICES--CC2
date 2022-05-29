package com.example.retrywithjedis.domain.payment;

import com.example.retrywithjedis.kernel.ValueObjectId;

public class PaymentId implements ValueObjectId {

        private final int value;

        private PaymentId(int value) {
            this.value = value;
        }

        public static PaymentId of(int value) {
            return new PaymentId(value);
        }
        public int getValue() {
            return value;
        }

    @Override
    public String toString() {
        return "PaymentId{" +
                "value=" + value +
                '}';
    }
}
