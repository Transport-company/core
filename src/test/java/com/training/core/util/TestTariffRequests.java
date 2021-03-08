package com.training.core.util;

import com.training.core.dto.request.TariffRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestTariffRequests {
    private TestTariffRequests() {
    }

    public static TariffRequest from2021() {
        return TariffRequest.builder()
                .effectiveDate(LocalDate.of(2020, 1, 1))
                .orderSum(new BigDecimal("120.00"))
                .courierSum(new BigDecimal("600.00"))
                .distancePrice(new BigDecimal("0.60"))
                .minDistance(15)
                .distanceThreshold(900)
                .reductionFactor(new BigDecimal("0.40"))
                .weightUnit(1f)
                .weightThreshold(1f)
                .weightRatioIncrease(0.1f)
                .volumeUnit(0.1f)
                .volumeThreshold(0.125f)
                .volumeRatioIncrease(0.1f)
                .build();
    }

    public static TariffRequest from2020Update() {
        return TariffRequest.builder()
                .effectiveDate(LocalDate.of(2020, 1, 1))
                .orderSum(new BigDecimal("105.00"))
                .courierSum(new BigDecimal("495.00"))
                .distancePrice(new BigDecimal("0.50"))
                .minDistance(15)
                .distanceThreshold(999)
                .reductionFactor(new BigDecimal("0.50"))
                .weightUnit(1f)
                .weightThreshold(1f)
                .weightRatioIncrease(0.1f)
                .volumeUnit(0.1f)
                .volumeThreshold(0.125f)
                .volumeRatioIncrease(0.1f)
                .build();
    }
}
