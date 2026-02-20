package com.kelvyns.paymentservice.infrastructure.adapter.rest;

import com.kelvyns.paymentservice.application.usecase.CreatePaymentUseCase;
import com.kelvyns.paymentservice.application.usecase.GetPaymentByIdUseCase;
import com.kelvyns.paymentservice.domain.model.Payment;
import com.kelvyns.paymentservice.infrastructure.adapter.rest.dto.CreatePaymentRequest;
import com.kelvyns.paymentservice.infrastructure.adapter.rest.dto.PaymentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    
    private final CreatePaymentUseCase createPaymentUseCase;
    private final GetPaymentByIdUseCase getPaymentByIdUseCase;
    
    public PaymentController(CreatePaymentUseCase createPaymentUseCase, GetPaymentByIdUseCase getPaymentByIdUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.getPaymentByIdUseCase = getPaymentByIdUseCase;
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
    
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable UUID id) {
        return getPaymentByIdUseCase.execute(id)
                .map(payment -> ResponseEntity.ok(new PaymentResponse(
                        payment.getId(),
                        payment.getInvoiceId(),
                        payment.getAmount(),
                        payment.getCreatedAt()
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}
