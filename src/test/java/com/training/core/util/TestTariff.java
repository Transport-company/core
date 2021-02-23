package com.training.core.util;

import com.training.core.model.Tariff;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestTariff {
    private TestTariff() {
    }

    public static Tariff first() {
        return Tariff.builder()
                .id(1L)
                .effectiveDate(LocalDate.of(1900, 1, 1))
                .orderSum(new BigDecimal("100.00"))
                .courierSum(new BigDecimal("500.00"))
                .distancePrice(new BigDecimal("0.50"))
                .minDistance(10)
                .distanceThreshold(800)
                .reductionFactor(new BigDecimal("0.5"))
                .build();
    }
}
