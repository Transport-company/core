package com.training.core.util;

import com.training.core.model.Tariff;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestTariffs {
    private TestTariffs() {
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
                .reductionFactor(new BigDecimal("0.50"))
                .weightUnit(1f)
                .weightThreshold(1f)
                .weightRatioIncrease(0.1f)
                .volumeUnit(0.1f)
                .volumeThreshold(0.125f)
                .volumeRatioIncrease(0.1f)
                .build();
    }

    public static Tariff from2020() {
        return Tariff.builder()
                .id(2L)
                .effectiveDate(LocalDate.of(2020, 1, 1))
                .orderSum(new BigDecimal("110.00"))
                .courierSum(new BigDecimal("490.00"))
                .distancePrice(new BigDecimal("0.50"))
                .minDistance(12)
                .distanceThreshold(1000)
                .reductionFactor(new BigDecimal("0.5"))
                .weightUnit(1f)
                .weightThreshold(1f)
                .weightRatioIncrease(0.1f)
                .volumeUnit(0.1f)
                .volumeThreshold(0.125f)
                .volumeRatioIncrease(0.1f)
                .build();
    }

    public static List<Tariff> list() {
        List<Tariff> list = new ArrayList<>();
        list.add(first());
        list.add(from2020());

        return list;
    }
}
