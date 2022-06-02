package io.swagger.adapters.primary;

import io.swagger.adapters.primary.request.PaymentRequest;
import io.swagger.adapters.primary.response.PaymentResponse;
import io.swagger.adapters.primary.response.PaymentResponseAdapter;
import io.swagger.application.CreatePaymentCommand;
import io.swagger.application.OrderDTO;
import io.swagger.application.RetrievePaymentRequest;
import io.swagger.application.RetrieveTransactionRequest;
import io.swagger.application.dto.BuyerInfoDTO;
import io.swagger.application.dto.CreditCardInfoDTO;
import io.swagger.domain.payment.Payment;
import io.swagger.domain.payment.PaymentId;
import io.swagger.domain.payment.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PaymentController implements PaymentsApi{

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    private final PaymentResponseAdapter paymentResponseAdapter;
    public PaymentController(PaymentService paymentService, PaymentResponseAdapter paymentResponseAdapter) {
        this.paymentService = paymentService;
        this.paymentResponseAdapter = paymentResponseAdapter;
    }

    public ResponseEntity<Void> createPayment(@RequestBody PaymentRequest paymentRequest) {

        List<OrderDTO> orderDTOList = new ArrayList<>();
        paymentRequest.getPaymentOrders().forEach(order-> {
            orderDTOList.add(new OrderDTO(order.getId(), order.getName()));
        });
        CreatePaymentCommand createPaymentCommand = CreatePaymentCommand.of(
                paymentRequest.getTransactionUUID(),
                new BuyerInfoDTO(
                        paymentRequest.getBuyerInfo().getFirstName(),
                        paymentRequest.getBuyerInfo().getLastName(),
                        paymentRequest.getBuyerInfo().getPhone(),
                        paymentRequest.getBuyerInfo().getAdress(),
                        paymentRequest.getBuyerInfo().getZipcode()),
                new CreditCardInfoDTO(paymentRequest.getCreditCardInfo().getCardNumber(),
                        paymentRequest.getCreditCardInfo().getExpirationDate(),
                        paymentRequest.getCreditCardInfo().getCardVerificationValue()),
                orderDTOList);

        PaymentId paymentId = paymentService.pay(createPaymentCommand);

        return ResponseEntity.created(linkTo(methodOn(PaymentController.class).getPaymentById(paymentId.getValue())).toUri()).build();
    }

    @Override
    public ResponseEntity<Void> deletePaymentById(int paymentId) {
        return null;
    }

    @GetMapping(value = "{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable int Id) {
        RetrievePaymentRequest retrievePaymentRequest = RetrievePaymentRequest.of(Id);

        Payment payment = paymentService.retrievePayment(retrievePaymentRequest);

        PaymentResponse paymentResponse = paymentResponseAdapter.adapt(payment);

        return ResponseEntity.ok(paymentResponse);
    }

    @GetMapping(value = "/status/{transactionUUID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPaymentStatusByTransactionUUID(@PathVariable String transactionUUID) {
        RetrieveTransactionRequest retrieveTransactionRequest = RetrieveTransactionRequest.of(transactionUUID);

        String transactionStatus = paymentService.retrieveTransaction(retrieveTransactionRequest);

        return ResponseEntity.ok(transactionStatus);
    }

    //todo delete and delete in cache
}
