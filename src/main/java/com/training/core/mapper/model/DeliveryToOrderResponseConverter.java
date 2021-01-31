package com.training.core.mapper.model;


import com.training.core.dto.response.OrderResponse;
import com.training.core.model.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class DeliveryToOrderResponseConverter implements Converter<Delivery, OrderResponse> {
    private final AddressToAddressResponceConverter toAddressResponceConverter;
    private final ClientToClientResponseConverter toClientResponseConverter;
    private final CargoToCargoResponseConverter toCargoResponseConverter;


    @Override
    public OrderResponse convert(Delivery delivery) {
        return OrderResponse.builder()
                .id(delivery.getId())
                .sender(toClientResponseConverter.convert(delivery.getSender()))
                .recipient(toClientResponseConverter.convert(delivery.getRecipient()))
                .cargo(toCargoResponseConverter.convert(delivery.getCargo()))
                .sendingAddress(toAddressResponceConverter.convert(delivery.getSendingAddress()))
                .shippingAddress(toAddressResponceConverter.convert(delivery.getShippingAddress()))
                .sum(delivery.getSum())
                .build();
    }
}
