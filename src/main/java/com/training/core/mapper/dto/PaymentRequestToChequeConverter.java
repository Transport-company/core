package com.training.core.mapper.dto;

import com.training.core.dto.request.PaymentRequest;
import com.training.core.model.Cheque;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class PaymentRequestToChequeConverter implements Converter<PaymentRequest, Cheque> {

    private final DeliveryRequestToDeliveryConverter deliveryRequestToDeliveryConverter;

    @Override
    public Cheque convert(PaymentRequest paymentRequest) {
        return Cheque.builder()
                .sum(paymentRequest.getSum())
                .delivery(deliveryRequestToDeliveryConverter.convert(paymentRequest.getDeliveryRequest()))
                .chequeFile(new byte[0])
                .build();
    }
}
