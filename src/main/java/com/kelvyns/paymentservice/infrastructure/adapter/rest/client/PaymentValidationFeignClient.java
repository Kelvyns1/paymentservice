package com.kelvyns.paymentservice.infrastructure.adapter.rest.client;

import com.kelvyns.paymentservice.infrastructure.adapter.rest.external.PaymentValidationRequest;
import com.kelvyns.paymentservice.infrastructure.adapter.rest.external.PaymentValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-validation-client", url = "http://localhost:8080")
public interface PaymentValidationFeignClient {
    
    @PostMapping("/external/payment-validation")
    PaymentValidationResponse validatePayment(@RequestBody PaymentValidationRequest request);
}
