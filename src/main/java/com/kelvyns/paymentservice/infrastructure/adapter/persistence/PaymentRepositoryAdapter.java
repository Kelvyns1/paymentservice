package com.kelvyns.paymentservice.infrastructure.adapter.persistence;

import com.kelvyns.paymentservice.application.port.out.PaymentRepositoryPort;
import com.kelvyns.paymentservice.domain.model.Payment;
import com.kelvyns.paymentservice.infrastructure.adapter.persistence.entity.PaymentEntity;
import com.kelvyns.paymentservice.infrastructure.adapter.persistence.repository.SpringDataPaymentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {
    
    private final SpringDataPaymentRepository repository;
    
    public PaymentRepositoryAdapter(SpringDataPaymentRepository repository) {
        this.repository = repository;
    }
    
    @Transactional
    @Override
    public Payment save(Payment payment) {
        PaymentEntity entity = toEntity(payment);
        PaymentEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<Payment> findById(UUID id) {
        return repository.findById(id)
                .map(this::toDomain);
    }
    
    @Override
    public List<Payment> findByInvoiceId(String invoiceId) {
        return repository.findByInvoiceId(invoiceId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    private PaymentEntity toEntity(Payment payment) {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(payment.getId());
        entity.setInvoiceId(payment.getInvoiceId());
        entity.setAmount(payment.getAmount());
        entity.setCreatedAt(payment.getCreatedAt());
        return entity;
    }
    
    private Payment toDomain(PaymentEntity entity) {
        return new Payment(
                entity.getId(),
                entity.getInvoiceId(),
                entity.getAmount(),
                entity.getCreatedAt()
        );
    }
}
