package com.training.core.mapper;

import com.training.core.dto.request.OrderRequest;
import com.training.core.model.Delivery;
import org.springframework.core.convert.converter.Converter;


public class OrderRequestToDeliveryConverter implements Converter<OrderRequest, Delivery> {

    @Override
    public Delivery convert(OrderRequest orderRequest) {
        return Delivery.builder()
                .cargo(orderRequest.getCargo())
//                .sender(orderRequest.getSender())
//                .recipient(orderRequest.getRecipient())
//                .sendingAddress(orderRequest.getSendingAddress())
//                .shippingAddress(orderRequest.getShippingAddress())
                .build();
    }

}
