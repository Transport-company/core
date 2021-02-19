package com.training.core.util;

import com.training.core.dto.request.CargoRequest;

import java.math.BigDecimal;

public class TestCargoRequests {
    private TestCargoRequests() {
    }

    public static CargoRequest first() {
        return CargoRequest.builder()
                .weight(0.4f)
                .declaredValue(new BigDecimal("1000.00"))
                .length(15f)
                .width(10f)
                .height(5f)
                .build();
    }

    public static CargoRequest second() {
        return CargoRequest.builder()
                .weight(1.2f)
                .declaredValue(new BigDecimal("1500.00"))
                .length(25f)
                .width(12f)
                .height(10f)
                .build();
    }
}
