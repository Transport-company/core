package com.training.core.mapper.model;

import com.training.core.dto.response.PaymentResponse;
import com.training.core.model.Cheque;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChequeToPaymentResponseConverter implements Converter<Cheque, PaymentResponse> {

    private final DeliveryToDeliveryResponseConverter deliveryToDeliveryResponseConverter;

    @Override
    public PaymentResponse convert(Cheque cheque) {
        return PaymentResponse.builder()
                .sum(cheque.getSum())
                .deliveryResponse(deliveryToDeliveryResponseConverter.convert(cheque.getDelivery()))
                .chequeFile(cheque.getChequeFile())
                .build();
    }
}
