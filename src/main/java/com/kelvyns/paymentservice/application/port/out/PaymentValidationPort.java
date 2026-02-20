package com.kelvyns.paymentservice.application.port.out;

import java.math.BigDecimal;

public interface PaymentValidationPort {
    
    void validate(BigDecimal amount);
}
