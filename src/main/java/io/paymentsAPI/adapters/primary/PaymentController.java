package io.paymentsAPI.adapters.primary;

import io.paymentsAPI.adapters.primary.request.PaymentRequest;
import io.paymentsAPI.adapters.primary.response.PaymentResponse;
import io.paymentsAPI.application.CreatePaymentCommand;
import io.paymentsAPI.application.RetrievePaymentRequest;
import io.paymentsAPI.application.RetrieveTransactionRequest;
import io.paymentsAPI.domain.payment.PaymentId;
import io.paymentsAPI.domain.payment.PaymentService;
import io.paymentsAPI.domain.payment.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PaymentController implements PaymentsApi{

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public ResponseEntity<Void> createPayment(@RequestBody PaymentRequest paymentRequest) {

        CreatePaymentCommand createPaymentCommand = CreatePaymentCommand.of(null, -1L, null, null, null, null, null);

        PaymentId paymentId = paymentService.pay(createPaymentCommand);

        return ResponseEntity.created(linkTo(methodOn(PaymentController.class).getPaymentById(paymentId.getValue())).toUri()).build();
    }

    @Override
    public ResponseEntity<Void> deletePaymentById(Long paymentId) {
        return null;
    }

    @GetMapping(value = "{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long Id) {
        RetrievePaymentRequest retrievePaymentRequest = RetrievePaymentRequest.of(Id);

        Payment payment = paymentService.retrievePayment(retrievePaymentRequest);
        //TODO payment response adapter
        return null;
    }

    @GetMapping(value = "/status/{transactionUUID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPaymentStatusByTransactionUUID(@PathVariable String transactionUUID) {
        RetrieveTransactionRequest retrieveTransactionRequest = RetrieveTransactionRequest.of(transactionUUID);

        String transactionStatus = paymentService.retrieveTransaction(retrieveTransactionRequest);

        return ResponseEntity.ok(transactionStatus);
    }

    //todo delete and delete in cache
}
