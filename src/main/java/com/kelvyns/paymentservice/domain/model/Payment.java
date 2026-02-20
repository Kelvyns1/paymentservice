package com.kelvyns.paymentservice.domain.model;

import com.kelvyns.paymentservice.domain.exception.InvalidAmountException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Payment {
    
    private final UUID id;
    private final String invoiceId;
    private final BigDecimal amount;
    private final Instant createdAt;
    
    public Payment(UUID id, String invoiceId, BigDecimal amount, Instant createdAt) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (invoiceId == null || invoiceId.isBlank()) {
            throw new IllegalArgumentException("InvoiceId cannot be null or blank");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
        
        this.id = id;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.createdAt = createdAt;
    }
    
    public UUID getId() {
        return id;
    }
    
    public String getInvoiceId() {
        return invoiceId;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public Instant getCreatedAt() {
        return createdAt;
    }
}
