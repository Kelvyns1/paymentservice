package com.kelvyns.paymentservice.application.usecase;

import com.kelvyns.paymentservice.application.port.out.PaymentRepositoryPort;
import com.kelvyns.paymentservice.domain.model.Payment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Component
public class CreatePaymentUseCase {
    
    private final PaymentRepositoryPort paymentRepositoryPort;
    
    public CreatePaymentUseCase(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
    }
    
    public Payment execute(String invoiceId, BigDecimal amount) {
        UUID id = UUID.randomUUID();
        Instant createdAt = Instant.now();
        
        Payment payment = new Payment(id, invoiceId, amount, createdAt);
        
        return paymentRepositoryPort.save(payment);
    }
}
