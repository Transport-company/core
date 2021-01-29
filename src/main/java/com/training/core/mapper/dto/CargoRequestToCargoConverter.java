package com.training.core.mapper.dto;

import com.training.core.dto.request.CargoRequest;
import com.training.core.model.Cargo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CargoRequestToCargoConverter implements Converter<CargoRequest, Cargo> {

    @Override
    public Cargo convert(CargoRequest cargoRequest) {
        return Cargo.builder()
                .weight(cargoRequest.getWeight())
                .declaredValue(cargoRequest.getDeclaredValue())
                .length(cargoRequest.getLength())
                .width(cargoRequest.getWidth())
                .height(cargoRequest.getHeight())
                .build();
    }

}
