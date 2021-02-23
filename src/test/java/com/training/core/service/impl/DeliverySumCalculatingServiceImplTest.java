package com.training.core.service.impl;

import com.training.core.model.Tariff;
import com.training.core.service.TariffService;
import com.training.core.util.TestTariff;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DeliverySumCalculatingServiceImplTest extends BaseTest {

    @InjectMocks
    private DeliverySumCalculatingServiceImpl sumCalculatingService;

    @Mock
    private TariffService tariffService;

    @Test
    void givenDistanceLessThanMin_whenGetSum_thenCalculateSum() {

        int distance = 1;
        Tariff tariff = TestTariff.first();

        BigDecimal manualSum = tariff.getOrderSum().add(tariff.getCourierSum());
        BigDecimal distanceSum = BigDecimal.valueOf(tariff.getMinDistance())
                .multiply(tariff.getDistancePrice())
                .setScale(2, RoundingMode.CEILING);
        manualSum = manualSum.add(distanceSum);

        when(tariffService.getForDate(any()))
                .thenReturn(tariff);

        BigDecimal sum = sumCalculatingService.getSum(distance, LocalDate.now());

        assertNotNull(sum);
        assertEquals(manualSum, sum);
    }

    @Test
    void givenDistanceMoreThanMinAndLessThanThreshold_whenGetSum_thenCalculateSum() {

        int distance = 200;
        Tariff tariff = TestTariff.first();

        BigDecimal manualSum = tariff.getOrderSum().add(tariff.getCourierSum());
        BigDecimal distanceSum = BigDecimal.valueOf(distance)
                .multiply(tariff.getDistancePrice())
                .setScale(2, RoundingMode.CEILING);
        manualSum = manualSum.add(distanceSum);

        when(tariffService.getForDate(any()))
                .thenReturn(tariff);

        BigDecimal sum = sumCalculatingService.getSum(distance, LocalDate.now());

        assertNotNull(sum);
        assertEquals(manualSum, sum);
    }

    @Test
    void givenDistanceMoreThanThreshold_whenGetSum_thenCalculateSum() {

        int distance = 2000;
        Tariff tariff = TestTariff.first();

        BigDecimal manualSum = tariff.getOrderSum().add(tariff.getCourierSum());
        BigDecimal distanceSum = BigDecimal.valueOf(tariff.getDistanceThreshold())
                .multiply(tariff.getDistancePrice())
                .setScale(2, RoundingMode.CEILING);
        distanceSum = distanceSum.add(BigDecimal.valueOf(distance - tariff.getDistanceThreshold())
                .multiply(tariff.getDistancePrice())
                .multiply(tariff.getReductionFactor())
                .setScale(2, RoundingMode.CEILING));
        manualSum = manualSum.add(distanceSum);

        when(tariffService.getForDate(any()))
                .thenReturn(tariff);

        BigDecimal sum = sumCalculatingService.getSum(distance, LocalDate.now());

        assertNotNull(sum);
        assertEquals(manualSum, sum);
    }
}