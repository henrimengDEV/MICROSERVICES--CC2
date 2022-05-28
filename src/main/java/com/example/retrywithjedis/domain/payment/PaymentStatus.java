package com.example.retrywithjedis.domain.payment;

public enum PaymentStatus {
    IN_PROGRESS("in progress"),
    SUCCESS("success"),
    FAILED("failed");

    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}


