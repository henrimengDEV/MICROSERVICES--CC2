package io.paymentsAPI.application;

import io.paymentsAPI.application.dto.BuyerInfoDTO;
import io.paymentsAPI.application.dto.CreditCardInfoDTO;

import java.util.List;

public class CreatePaymentCommand {

    private final String uuid;
    private final Long paymentId;
    private final BuyerInfoDTO buyerInfo;
    private final String checkoutId;
    private final CreditCardInfoDTO creditCardInfo;
    private final List<String> paymentOrders;
    private final String status;

    private CreatePaymentCommand(String uuid, Long paymentId, BuyerInfoDTO buyerInfo, String checkoutId, CreditCardInfoDTO creditCardInfo, List<String> paymentOrders, String status) {
        this.uuid = uuid;
        this.paymentId = paymentId;
        this.buyerInfo = buyerInfo;
        this.checkoutId = checkoutId;
        this.creditCardInfo = creditCardInfo;
        this.paymentOrders = paymentOrders;
        this.status = status;
    }

    public static CreatePaymentCommand of(String uuid, Long paymentId, BuyerInfoDTO buyerInfo, String checkoutId, CreditCardInfoDTO creditCardInfo, List<String> paymentOrders, String status) {
        return new CreatePaymentCommand(uuid, paymentId, buyerInfo, checkoutId, creditCardInfo, paymentOrders, status);
    }

    public String getUuid() {
        return uuid;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public BuyerInfoDTO getBuyerInfo() {
        return buyerInfo;
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public CreditCardInfoDTO getCreditCardInfo() {
        return creditCardInfo;
    }

    public List<String> getPaymentOrders() {
        return paymentOrders;
    }

    public String getStatus() {
        return status;
    }
}
