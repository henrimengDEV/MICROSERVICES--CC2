package com.example.retrywithjedis.domain.order;

import com.example.retrywithjedis.kernel.ValueObjectId;

public class OrderId implements ValueObjectId {

        private final int value;

        public OrderId(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
}
