package com.training.core.mapper;


import com.training.core.dto.response.OrderPageResponse;
import com.training.core.dto.response.OrderResponse;
import org.springframework.core.convert.converter.Converter;


import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderResponseStreamToOrderPageResponseConverter implements Converter<Stream<OrderResponse>, OrderPageResponse> {



    @Override
    public OrderPageResponse convert(Stream<OrderResponse> stream) {
        return OrderPageResponse.builder()
                .content(stream.collect(Collectors.toList()))

                .build();
    }
}
