package io.swagger.adapters.primary.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;

/**
 * CreditCardInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-06-01T16:22:31.174Z")


public class CreditCardInfoRequest {
    @JsonProperty("card_number")
    private Long cardNumber = null;

    @JsonProperty("expirationDate")
    @ApiModelProperty(required = true,example = "12-04-2022")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expirationDate = null;

    @JsonProperty("card_verification_value")
    private Long cardVerificationValue = null;

    public CreditCardInfoRequest cardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    /**
     * Get cardNumber
     *
     * @return cardNumber
     **/
    @ApiModelProperty(value = "")


    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CreditCardInfoRequest expirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    /**
     * Get expirationDate
     *
     * @return expirationDate
     **/
    @ApiModelProperty(value = "")

    @Valid

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CreditCardInfoRequest cardVerificationValue(Long cardVerificationValue) {
        this.cardVerificationValue = cardVerificationValue;
        return this;
    }

    /**
     * Get cardVerificationValue
     *
     * @return cardVerificationValue
     **/
    @ApiModelProperty(value = "")


    public Long getCardVerificationValue() {
        return cardVerificationValue;
    }

    public void setCardVerificationValue(Long cardVerificationValue) {
        this.cardVerificationValue = cardVerificationValue;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreditCardInfoRequest creditCardInfoRequest = (CreditCardInfoRequest) o;
        return Objects.equals(this.cardNumber, creditCardInfoRequest.cardNumber) &&
                Objects.equals(this.expirationDate, creditCardInfoRequest.expirationDate) &&
                Objects.equals(this.cardVerificationValue, creditCardInfoRequest.cardVerificationValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, expirationDate, cardVerificationValue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreditCardInfo {\n");

        sb.append("    cardNumber: ").append(toIndentedString(cardNumber)).append("\n");
        sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
        sb.append("    cardVerificationValue: ").append(toIndentedString(cardVerificationValue)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

