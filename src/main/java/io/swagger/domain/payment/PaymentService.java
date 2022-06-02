package io.swagger.domain.payment;

import io.swagger.application.CreatePaymentCommand;
import io.swagger.application.RetrievePaymentRequest;
import io.swagger.application.RetrieveTransactionRequest;

public interface PaymentService {

    PaymentId pay(CreatePaymentCommand createPaymentCommand);

    Payment retrievePayment(RetrievePaymentRequest retrievePaymentRequest);

    String retrieveTransaction(RetrieveTransactionRequest retrieveTransactionRequest);


}
