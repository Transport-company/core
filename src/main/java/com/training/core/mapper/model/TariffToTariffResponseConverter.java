package com.training.core.mapper.model;

import com.training.core.dto.response.TariffResponse;
import com.training.core.model.Tariff;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TariffToTariffResponseConverter implements Converter<Tariff, TariffResponse> {
    @Override
    public TariffResponse convert(Tariff tariff) {
        return TariffResponse.builder()
                .id(tariff.getId())
                .effectiveDate(tariff.getEffectiveDate())
                .orderSum(tariff.getOrderSum())
                .courierSum(tariff.getCourierSum())
                .distancePrice(tariff.getDistancePrice())
                .minDistance(tariff.getMinDistance())
                .distanceThreshold(tariff.getDistanceThreshold())
                .reductionFactor(tariff.getReductionFactor())
                .weightUnit(tariff.getWeightUnit())
                .weightThreshold(tariff.getWeightThreshold())
                .weightRatioIncrease(tariff.getWeightRatioIncrease())
                .volumeUnit(tariff.getVolumeUnit())
                .volumeThreshold(tariff.getVolumeThreshold())
                .volumeRatioIncrease(tariff.getVolumeRatioIncrease())
                .created(tariff.getCreated())
                .updated(tariff.getUpdated())
                .build();
    }
}
