package com.example.retrywithjedis.domain.payment;

import com.example.retrywithjedis.kernel.ValueObjectId;

public class PaymentId implements ValueObjectId {

        private final int value;

        public PaymentId(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
}
