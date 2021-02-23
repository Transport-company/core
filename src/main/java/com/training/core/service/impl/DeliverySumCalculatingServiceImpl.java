package com.training.core.service.impl;

import com.training.core.exception.ErrorMessages;
import com.training.core.model.Tariff;
import com.training.core.service.DeliverySumCalculatingService;
import com.training.core.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliverySumCalculatingServiceImpl implements DeliverySumCalculatingService {
    private final TariffService tariffService;

    @Override
    public BigDecimal getSum(int distance, LocalDate date) {
        Assert.notNull(date, ErrorMessages.NULL_DATE.getErrorMessage());

        Tariff tariff = tariffService.getForDate(date);
        BigDecimal sum = tariff.getOrderSum()
                .add(tariff.getCourierSum())
                .add(BigDecimal.valueOf(distance < tariff.getMinDistance() ?
                        tariff.getMinDistance() :
                        (distance > tariff.getDistanceThreshold() ?
                                tariff.getDistanceThreshold() :
                                distance))
                        .multiply(tariff.getDistancePrice())
                        .setScale(2, RoundingMode.CEILING))
                .add(BigDecimal.valueOf(distance < tariff.getDistanceThreshold() ?
                        0 :
                        distance - tariff.getDistanceThreshold())
                        .multiply(tariff.getDistancePrice())
                        .multiply(tariff.getReductionFactor())
                        .setScale(2, RoundingMode.CEILING));
        log.info("The calculated sum of delivery for a distance {} is {}", distance, sum);
        return sum;
    }
}
