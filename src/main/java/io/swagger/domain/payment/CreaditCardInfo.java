package io.swagger.domain.payment;

import java.time.LocalDate;
import java.util.Objects;

public final class CreaditCardInfo {

    private final Long cardNumber;
    private final LocalDate expirationDate;
    private final Long cardVerificationValue;

    public CreaditCardInfo(Long cardNumber, LocalDate expirationDate, Long cardVerificationValue) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardVerificationValue = cardVerificationValue;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Long getCardVerificationValue() {
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
