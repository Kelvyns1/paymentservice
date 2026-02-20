package com.kelvyns.paymentservice.application.usecase;

import com.kelvyns.paymentservice.application.port.out.PaymentRepositoryPort;
import com.kelvyns.paymentservice.application.port.out.PaymentValidationPort;
import com.kelvyns.paymentservice.domain.model.Payment;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CreatePaymentUseCase {
    
    private final PaymentRepositoryPort paymentRepositoryPort;
    private final PaymentValidationPort paymentValidationPort;
    
    public CreatePaymentUseCase(
            PaymentRepositoryPort paymentRepositoryPort,
            PaymentValidationPort paymentValidationPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
        this.paymentValidationPort = paymentValidationPort;
    }
    
    public Payment execute(String invoiceId, BigDecimal amount) {
        // Validate payment with external service
        paymentValidationPort.validate(amount);
        
        UUID id = UUID.randomUUID();
        Instant createdAt = Instant.now();
        
        Payment payment = new Payment(id, invoiceId, amount, createdAt);
        
        return paymentRepositoryPort.save(payment);
    }
}
