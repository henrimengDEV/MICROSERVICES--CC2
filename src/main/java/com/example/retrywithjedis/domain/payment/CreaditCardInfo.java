package com.example.retrywithjedis.domain.payment;

import java.util.Objects;

public final class CreaditCardInfo {

    private final String cardNumber;
    private final String expirationDate;
    private final String cardVerificationValue;

    public CreaditCardInfo(String cardNumber, String expirationDate, String cardVerificationValue) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardVerificationValue = cardVerificationValue;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCardVerificationValue() {
        return cardVerificationValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreaditCardInfo that = (CreaditCardInfo) o;
        return Objects.equals(cardNumber, that.cardNumber) && Objects.equals(
            expirationDate,
            that.expirationDate
        ) && Objects.equals(cardVerificationValue, that.cardVerificationValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, expirationDate, cardVerificationValue);
    }
}
