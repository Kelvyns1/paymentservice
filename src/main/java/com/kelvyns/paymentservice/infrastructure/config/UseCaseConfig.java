package com.kelvyns.paymentservice.infrastructure.config;

import com.kelvyns.paymentservice.application.port.out.PaymentRepositoryPort;
import com.kelvyns.paymentservice.application.usecase.CreatePaymentUseCase;
import com.kelvyns.paymentservice.application.usecase.GetPaymentByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    
    @Bean
    public CreatePaymentUseCase createPaymentUseCase(
            PaymentRepositoryPort paymentRepositoryPort) {
        return new CreatePaymentUseCase(paymentRepositoryPort);
    }
    
    @Bean
    public GetPaymentByIdUseCase getPaymentByIdUseCase(
            PaymentRepositoryPort paymentRepositoryPort) {
        return new GetPaymentByIdUseCase(paymentRepositoryPort);
    }
}
