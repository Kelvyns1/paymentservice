package com.kelvyns.paymentservice.infrastructure.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class PaymentResponse {
    
    private final UUID id;
    private final String invoiceId;
    private final BigDecimal amount;
    private final Instant createdAt;
}
