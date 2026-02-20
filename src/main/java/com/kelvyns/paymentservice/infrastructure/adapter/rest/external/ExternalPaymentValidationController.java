package com.kelvyns.paymentservice.infrastructure.adapter.rest.external;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/external")
public class ExternalPaymentValidationController {
    
    private static final BigDecimal LIMIT = new BigDecimal("1000");
    
    @PostMapping("/payment-validation")
    public ResponseEntity<PaymentValidationResponse> validatePayment(
            @RequestBody PaymentValidationRequest request) {
        
        if (request.getAmount().compareTo(LIMIT) < 0) {
            return ResponseEntity.ok(new PaymentValidationResponse(true, null));
        } else {
            return ResponseEntity.badRequest()
                    .body(new PaymentValidationResponse(false, "Amount exceeds limit"));
        }
    }
}
