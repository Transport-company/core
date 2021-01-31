package com.training.core.mapper.model;

import com.training.core.dto.response.OrderPageResponse;
import com.training.core.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderPageToOrderPageResponseConverter implements Converter<Page<Delivery>, OrderPageResponse> {
    private final DeliveryToOrderResponseConverter toOrderResponseConverter;

    @Override
    public OrderPageResponse convert(Page<Delivery> page) {
        return OrderPageResponse.builder()
                .content(page.getContent().stream()
                        .map(toOrderResponseConverter::convert)
                        .collect(Collectors.toList()))
                .number(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

}
