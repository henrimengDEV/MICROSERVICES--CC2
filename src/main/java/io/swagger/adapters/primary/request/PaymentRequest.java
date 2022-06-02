package io.swagger.adapters.primary.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Payment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-06-01T16:22:31.174Z")


public class PaymentRequest {
    @JsonProperty("buyer_info")
    private BuyerInfoRequest buyerInfoRequest = null;

    @JsonProperty("transaction_UUID")
    private String transactionUUID = null;

    @JsonProperty("credit_card_info")
    private CreditCardInfoRequest creditCardInfoRequest = null;

    @JsonProperty("payment_orders")
    @Valid
    private List<OrderRequest> paymentOrderRequests = new ArrayList<OrderRequest>();

    public PaymentRequest buyerInfo(BuyerInfoRequest buyerInfoRequest) {
        this.buyerInfoRequest = buyerInfoRequest;
        return this;
    }

    /**
     * Get buyerInfo
     *
     * @return buyerInfo
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public BuyerInfoRequest getBuyerInfo() {
        return buyerInfoRequest;
    }

    public void setBuyerInfo(BuyerInfoRequest buyerInfoRequest) {
        this.buyerInfoRequest = buyerInfoRequest;
    }

    public PaymentRequest transactionUUID(String transactionUUID) {
        this.transactionUUID = transactionUUID;
        return this;
    }

    /**
     * Get transactionUUID
     *
     * @return transactionUUID
     **/
    @ApiModelProperty(example = "25e54d67-45a4-46ab-8f9f-25769f392de6", required = true, value = "")
    @NotNull


    public String getTransactionUUID() {
        return transactionUUID;
    }

    public void setTransactionUUID(String transactionUUID) {
        this.transactionUUID = transactionUUID;
    }

    public PaymentRequest creditCardInfo(CreditCardInfoRequest creditCardInfoRequest) {
        this.creditCardInfoRequest = creditCardInfoRequest;
        return this;
    }

    /**
     * Get creditCardInfo
     *
     * @return creditCardInfo
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public CreditCardInfoRequest getCreditCardInfo() {
        return creditCardInfoRequest;
    }

    public void setCreditCardInfo(CreditCardInfoRequest creditCardInfoRequest) {
        this.creditCardInfoRequest = creditCardInfoRequest;
    }

    public PaymentRequest paymentOrders(List<OrderRequest> paymentOrderRequests) {
        this.paymentOrderRequests = paymentOrderRequests;
        return this;
    }

    public PaymentRequest addPaymentOrdersItem(OrderRequest paymentOrdersItem) {
        this.paymentOrderRequests.add(paymentOrdersItem);
        return this;
    }

    /**
     * Get paymentOrders
     *
     * @return paymentOrders
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid

    public List<OrderRequest> getPaymentOrders() {
        return paymentOrderRequests;
    }

    public void setPaymentOrders(List<OrderRequest> paymentOrderRequests) {
        this.paymentOrderRequests = paymentOrderRequests;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentRequest paymentRequest = (PaymentRequest) o;
        return Objects.equals(this.buyerInfoRequest, paymentRequest.buyerInfoRequest) &&
                Objects.equals(this.transactionUUID, paymentRequest.transactionUUID) &&
                Objects.equals(this.creditCardInfoRequest, paymentRequest.creditCardInfoRequest) &&
                Objects.equals(this.paymentOrderRequests, paymentRequest.paymentOrderRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerInfoRequest, transactionUUID, creditCardInfoRequest, paymentOrderRequests);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Payment {\n");

        sb.append("    buyerInfo: ").append(toIndentedString(buyerInfoRequest)).append("\n");
        sb.append("    transactionUUID: ").append(toIndentedString(transactionUUID)).append("\n");
        sb.append("    creditCardInfo: ").append(toIndentedString(creditCardInfoRequest)).append("\n");
        sb.append("    paymentOrders: ").append(toIndentedString(paymentOrderRequests)).append("\n");
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

