package io.swagger.application.dto;

import java.time.LocalDate;

public class CreditCardInfoDTO {

    Long cardNumber;
    LocalDate expirationDate;
    Long cardVerificationValue;

    public CreditCardInfoDTO(Long cardNumber, LocalDate expirationDate, Long cardVerificationValue) {
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
}
