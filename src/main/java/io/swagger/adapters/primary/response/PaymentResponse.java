package io.swagger.adapters.primary.response;

import java.util.List;

public class PaymentResponse {

    String transactionUUID;
    int paymentId;
    String buyerFirstname;
    String buyerLastname;
    String buyerPhone;
    String buyerAddress;
    String buyerZipcode;
    List<String> paymentOrders;
    String status;

    public PaymentResponse(String transactionUUID,
                           int paymentId,
                           String buyerFirstname,
                           String buyerLastname,
                           String buyerPhone,
                           String buyerAddress,
                           String buyerZipcode,
                           List<String> paymentOrders,
                           String status) {
        this.transactionUUID = transactionUUID;
        this.paymentId = paymentId;
        this.buyerFirstname = buyerFirstname;
        this.buyerLastname = buyerLastname;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.buyerZipcode = buyerZipcode;
        this.paymentOrders = paymentOrders;
        this.status = status;
    }

    public String getTransactionUUID() {
        return transactionUUID;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public String getBuyerFirstname() {
        return buyerFirstname;
    }

    public String getBuyerLastname() {
        return buyerLastname;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public String getBuyerZipcode() {
        return buyerZipcode;
    }

    public List<String> getPaymentOrders() {
        return paymentOrders;
    }

    public String getStatus() {
        return status;
    }
}
