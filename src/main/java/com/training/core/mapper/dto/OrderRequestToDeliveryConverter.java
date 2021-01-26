package com.training.core.mapper.dto;

import com.training.core.dto.request.OrderRequest;
import com.training.core.model.Delivery;
import com.training.core.service.DeliverySumCalculatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;


import static com.training.core.model.DeliveryStatus.REGISTERED;
 @RequiredArgsConstructor
public class OrderRequestToDeliveryConverter implements Converter<OrderRequest, Delivery> {
    private final DeliverySumCalculatingService deliverySumCalculatingService;


    @Override
    public Delivery convert(OrderRequest orderRequest) {
        return Delivery.builder()
                .cargo(orderRequest.getCargo())
                .sender(orderRequest.getSender())
                .recipient(orderRequest.getRecipient())
                .sendingAddress(orderRequest.getSendingAddress())
                .shippingAddress(orderRequest.getShippingAddress())
                .enabledNotifications(false)
                .trackingNumber("")
                .sum(deliverySumCalculatingService.getSum())
                .isPaid(false)
                .status(REGISTERED)
                .build();
    }
}
