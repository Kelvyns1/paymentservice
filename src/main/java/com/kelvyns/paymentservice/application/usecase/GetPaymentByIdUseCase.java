package com.kelvyns.paymentservice.application.usecase;

import com.kelvyns.paymentservice.application.port.out.PaymentRepositoryPort;
import com.kelvyns.paymentservice.domain.model.Payment;

import java.util.Optional;
import java.util.UUID;

public class GetPaymentByIdUseCase {
    
    private final PaymentRepositoryPort paymentRepositoryPort;
    
    public GetPaymentByIdUseCase(PaymentRepositoryPort paymentRepositoryPort) {
        this.paymentRepositoryPort = paymentRepositoryPort;
    }
    
    public Optional<Payment> execute(UUID id) {
        return paymentRepositoryPort.findById(id);
    }
}
