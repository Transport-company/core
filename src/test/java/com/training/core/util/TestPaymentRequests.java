package com.training.core.util;

import com.training.core.dto.request.PaymentRequest;

import java.math.BigDecimal;

public class TestPaymentRequests {

    public TestPaymentRequests() {
    }

    public static PaymentRequest getPaymentRequest() {
        return PaymentRequest.builder()
                .sum(BigDecimal.valueOf(120))
                .deliveryId(1L)
                .build();
    }
}
