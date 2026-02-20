package com.kelvyns.paymentservice.infrastructure.adapter.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CreatePaymentRequest {
    
    @NotBlank(message = "Invoice ID must not be blank")
    private String invoiceId;
    
    @NotNull(message = "Amount must not be null")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
}
