package io.paymentsAPI.domain.payment;

import io.paymentsAPI.application.CreatePaymentCommand;
import io.paymentsAPI.application.RetrievePaymentRequest;
import io.paymentsAPI.application.RetrieveTransactionRequest;

public interface PaymentService {

    PaymentId pay(CreatePaymentCommand createPaymentCommand);

    Payment retrievePayment(RetrievePaymentRequest retrievePaymentRequest);

    String retrieveTransaction(RetrieveTransactionRequest retrieveTransactionRequest);


}
