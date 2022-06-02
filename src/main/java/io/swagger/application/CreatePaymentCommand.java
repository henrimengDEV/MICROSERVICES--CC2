package io.swagger.application;

import io.swagger.application.dto.BuyerInfoDTO;
import io.swagger.application.dto.CreditCardInfoDTO;

import java.util.List;

public class CreatePaymentCommand {

    private final String transactionUUID;
    private final BuyerInfoDTO buyerInfo;
    private final CreditCardInfoDTO creditCardInfo;
    private final List<OrderDTO> paymentOrders;

    private CreatePaymentCommand(String transactionUUID, BuyerInfoDTO buyerInfo, CreditCardInfoDTO creditCardInfo, List<OrderDTO> paymentOrders) {
        this.transactionUUID = transactionUUID;
        this.buyerInfo = buyerInfo;
        this.creditCardInfo = creditCardInfo;
        this.paymentOrders = paymentOrders;
    }

    public static CreatePaymentCommand of(String uuid, BuyerInfoDTO buyerInfo, CreditCardInfoDTO creditCardInfo, List<OrderDTO> paymentOrders) {
        return new CreatePaymentCommand(uuid, buyerInfo, creditCardInfo, paymentOrders);
    }

    public String getTransactionUUID() {
        return transactionUUID;
    }

    public BuyerInfoDTO getBuyerInfo() {
        return buyerInfo;
    }

    public CreditCardInfoDTO getCreditCardInfo() {
        return creditCardInfo;
    }

    public List<OrderDTO> getPaymentOrders() {
        return paymentOrders;
    }

}
