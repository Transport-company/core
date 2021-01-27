package com.training.core.mapper.model;

import com.training.core.dto.response.DeliveryPageResponse;
import com.training.core.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PageToDeliveryPageResponse implements Converter<Page<Delivery>, DeliveryPageResponse> {
    private final DeliveryToDeliveryResponseConverter toDeliveryResponseConverter;

    @Override
    public DeliveryPageResponse convert(Page<Delivery> page) {
        return DeliveryPageResponse.builder()
                .content(page.getContent().stream()
                        .map(toDeliveryResponseConverter::convert)
                        .collect(Collectors.toList()))
                .size(page.getSize())
                .number(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

}
