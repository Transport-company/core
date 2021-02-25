package com.training.core.util;

import com.training.core.model.Cargo;

import java.math.BigDecimal;

public class TestCargo {
    private TestCargo() {
    }

    public static Cargo first() {
        return Cargo.builder()
                .weight(0.4f)
                .declaredValue(new BigDecimal("1000.00"))
                .length(15f)
                .width(10f)
                .height(5f)
                .build();
    }
}
