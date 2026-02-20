package com.kelvyns.paymentservice.infrastructure.adapter.rest.adapter;

import com.kelvyns.paymentservice.application.port.out.PaymentValidationPort;
import com.kelvyns.paymentservice.domain.exception.PaymentValidationException;
import com.kelvyns.paymentservice.infrastructure.adapter.rest.client.PaymentValidationFeignClient;
import com.kelvyns.paymentservice.infrastructure.adapter.rest.external.PaymentValidationRequest;
import com.kelvyns.paymentservice.infrastructure.adapter.rest.external.PaymentValidationResponse;
import feign.FeignException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentValidationAdapter implements PaymentValidationPort {
    
    private final PaymentValidationFeignClient feignClient;
    
    public PaymentValidationAdapter(PaymentValidationFeignClient feignClient) {
        this.feignClient = feignClient;
    }
    
    @Override
    public void validate(BigDecimal amount) {
        try {
            PaymentValidationRequest request = new PaymentValidationRequest(amount);
            PaymentValidationResponse response = feignClient.validatePayment(request);
            
            if (!response.isValid()) {
                throw new PaymentValidationException(
                        "Payment validation failed: " + response.getReason()
                );
            }
        } catch (FeignException ex) {
            throw new PaymentValidationException(
                    "External validation service error: " + ex.getMessage(),
                    ex
            );
        }
    }
}
