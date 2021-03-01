package com.training.core.mapper.model;

import com.training.core.dto.response.CargoResponse;
import com.training.core.model.Cargo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CargoToCargoResponseConverter implements Converter<Cargo, CargoResponse> {

    @Override
    public CargoResponse convert(Cargo cargo) {
        return CargoResponse.builder()
                .id(cargo.getId())
                .weight(cargo.getWeight())
                .declaredValue(cargo.getDeclaredValue())
                .length(cargo.getLength())
                .width(cargo.getWidth())
                .height(cargo.getHeight())
                .created(cargo.getCreated())
                .updated(cargo.getUpdated())
                .build();
    }

}
