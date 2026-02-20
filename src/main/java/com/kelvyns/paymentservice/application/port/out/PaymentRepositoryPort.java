package com.kelvyns.paymentservice.application.port.out;

import com.kelvyns.paymentservice.domain.model.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepositoryPort {
    
    Payment save(Payment payment);
    
    Optional<Payment> findById(UUID id);
    
    List<Payment> findByInvoiceId(String invoiceId);
}
