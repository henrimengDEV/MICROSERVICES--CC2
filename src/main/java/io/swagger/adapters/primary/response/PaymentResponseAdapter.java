package io.swagger.adapters.primary.response;

import io.swagger.domain.Order;
import io.swagger.domain.payment.Payment;
import io.swagger.kernel.Adapter;

import java.util.stream.Collectors;

public class PaymentResponseAdapter implements Adapter<Payment, PaymentResponse> {
    @Override
    public PaymentResponse adapt(Payment source) {
        return new PaymentResponse(
                source.getUuid().toString(),
                source.getPaymentId().getValue(),
                source.getBuyerInfo().getFirstname(),
                source.getBuyerInfo().getLastname(),
                source.getBuyerInfo().getPhone(),
                source.getBuyerInfo().getAddress(),
                source.getBuyerInfo().getZipcode(),
                source.getPaymentOrders().stream().map(Order::getName).collect(Collectors.toList()),
                source.getStatus().getValue()
        );
    }
}
