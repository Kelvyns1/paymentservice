package com.kelvyns.paymentservice.infrastructure.config;

import com.kelvyns.paymentservice.application.port.out.PaymentRepositoryPort;
import com.kelvyns.paymentservice.application.port.out.PaymentValidationPort;
import com.kelvyns.paymentservice.application.usecase.CreatePaymentUseCase;
import com.kelvyns.paymentservice.application.usecase.GetPaymentByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    
    @Bean
    public CreatePaymentUseCase createPaymentUseCase(
            PaymentRepositoryPort paymentRepositoryPort,
            PaymentValidationPort paymentValidationPort) {
        return new CreatePaymentUseCase(paymentRepositoryPort, paymentValidationPort);
    }
    
    @Bean
    public GetPaymentByIdUseCase getPaymentByIdUseCase(
            PaymentRepositoryPort paymentRepositoryPort) {
        return new GetPaymentByIdUseCase(paymentRepositoryPort);
    }
}
