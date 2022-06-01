package io.paymentsAPI.application.dto;

public class CreditCardInfoDTO {

    String cardNumber;
    String expirationDate;
    String cardVerificationValue;

    public CreditCardInfoDTO(String cardNumber, String expirationDate, String cardVerificationValue) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardVerificationValue = cardVerificationValue;
    }
}
