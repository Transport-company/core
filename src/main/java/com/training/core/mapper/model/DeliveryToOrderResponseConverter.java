package com.training.core.mapper.model;


import com.training.core.dto.response.OrderResponse;
import com.training.core.model.Delivery;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeliveryToOrderResponseConverter implements Converter<Delivery, OrderResponse> {


    @Override
    public OrderResponse convert(Delivery delivery) {
        return OrderResponse.builder()
                .id(delivery.getId())
                .sender(delivery.getSender())
                .recipient(delivery.getRecipient())
                .cargo(delivery.getCargo())
                .sendingAddress(delivery.getSendingAddress())
                .shippingAddress(delivery.getShippingAddress())
                .sum(delivery.getSum())
                .build();
    }
}
