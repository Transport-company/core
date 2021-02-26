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
    public BigDecimal getSum(int distance, float weight, float volume, LocalDate date) {
        Assert.isTrue(distance > 0, ErrorMessages.NOT_GREATER_ZERO_DISTANCE.getErrorMessage());
        Assert.isTrue(weight > 0, ErrorMessages.NOT_GREATER_ZERO_WEIGHT.getErrorMessage());
        Assert.isTrue(volume > 0, ErrorMessages.NOT_GREATER_ZERO_VOLUME.getErrorMessage());
        Assert.notNull(date, ErrorMessages.NULL_DATE.getErrorMessage());

        Tariff tariff = tariffService.getForDate(date);

        BigDecimal basicSum = getBasicSum(distance, tariff);
        BigDecimal weightRatioIncreasedSum = getWeightRatioIncreasedSum(basicSum, tariff, weight);
        BigDecimal volumeRatioIncreasedSum = getVolumeRatioIncreasedSum(basicSum, tariff, volume);

        BigDecimal sum = weightRatioIncreasedSum.max(volumeRatioIncreasedSum)
                .setScale(0, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.CEILING);
        log.info("The calculated sum of the delivery for a distance {} " +
                        "and the cargo with weight {} and volume {}  is {}",
                distance, weight, volume, sum);
        return sum;
    }

    private BigDecimal getBasicSum(int distance, Tariff tariff) {
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

        log.info("The calculated bacic sum of the delivery for a distance {} is {}",
                distance, sum);
        return sum;
    }

    private BigDecimal getWeightRatioIncreasedSum(
            BigDecimal basicSum, Tariff tariff, float weight) {
        if (weight <= tariff.getWeightThreshold()) {
            return basicSum;
        }

        if (tariff.getWeightUnit() == 0) {
            return basicSum;
        }

        double adds = Math.ceil((weight - tariff.getWeightThreshold()) / tariff.getWeightUnit());
        BigDecimal sum = basicSum
                .multiply(BigDecimal.valueOf(1.0 + adds * tariff.getWeightRatioIncrease()))
                .setScale(2, RoundingMode.CEILING);
        log.info("The sum increased due to the weight is {}", sum);
        return sum;
    }

    private BigDecimal getVolumeRatioIncreasedSum(
            BigDecimal basicSum, Tariff tariff, float volume) {
        if (volume <= tariff.getVolumeThreshold()) {
            return basicSum;
        }

        if (tariff.getVolumeUnit() == 0) {
            return basicSum;
        }

        double adds = Math.ceil((volume - tariff.getVolumeThreshold()) / tariff.getVolumeUnit());
        BigDecimal sum = basicSum
                .multiply(BigDecimal.valueOf(1.0 + adds * tariff.getVolumeRatioIncrease()))
                .setScale(2, RoundingMode.CEILING);
        log.info("The sum increased due to the volume is {}", sum);
        return sum;
    }
}
