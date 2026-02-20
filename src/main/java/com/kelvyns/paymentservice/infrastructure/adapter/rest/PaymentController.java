package com.kelvyns.paymentservice.infrastructure.adapter.rest;

import com.kelvyns.paymentservice.application.usecase.CreatePaymentUseCase;
import com.kelvyns.paymentservice.domain.model.Payment;
import com.kelvyns.paymentservice.infrastructure.adapter.rest.dto.CreatePaymentRequest;
import com.kelvyns.paymentservice.infrastructure.adapter.rest.dto.PaymentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    
    private final CreatePaymentUseCase createPaymentUseCase;
    
    public PaymentController(CreatePaymentUseCase createPaymentUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
    }
    
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request) {
        Payment payment = createPaymentUseCase.execute(request.getInvoiceId(), request.getAmount());
        
        PaymentResponse response = new PaymentResponse(
                payment.getId(),
                payment.getInvoiceId(),
                payment.getAmount(),
                payment.getCreatedAt()
        );
        
        return ResponseEntity
                .created(URI.create("/payments/" + payment.getId()))
                .body(response);
    }
}
