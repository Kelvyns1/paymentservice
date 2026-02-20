package com.kelvyns.paymentservice.infrastructure.adapter.rest.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentValidationRequest {
    
    private java.math.BigDecimal amount;
}
