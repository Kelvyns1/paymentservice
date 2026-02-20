package com.kelvyns.paymentservice.infrastructure.adapter.persistence.repository;

import com.kelvyns.paymentservice.infrastructure.adapter.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataPaymentRepository extends JpaRepository<PaymentEntity, UUID> {
    
    List<PaymentEntity> findByInvoiceId(String invoiceId);
}
