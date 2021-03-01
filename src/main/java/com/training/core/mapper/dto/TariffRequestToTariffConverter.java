package com.training.core.mapper.dto;

import com.training.core.dto.request.TariffRequest;
import com.training.core.model.Tariff;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TariffRequestToTariffConverter implements Converter<TariffRequest, Tariff> {

    @Override
    public Tariff convert(TariffRequest tariffRequest) {
        return Tariff.builder()
                .effectiveDate(tariffRequest.getEffectiveDate())
                .orderSum(tariffRequest.getOrderSum())
                .courierSum(tariffRequest.getCourierSum())
                .distancePrice(tariffRequest.getDistancePrice())
                .minDistance(tariffRequest.getMinDistance())
                .distanceThreshold(tariffRequest.getDistanceThreshold())
                .reductionFactor(tariffRequest.getReductionFactor())
                .build();
    }
}
