package com.training.core.config;

import com.training.core.mapper.dto.AddressRequestToAddressConverter;
import com.training.core.mapper.dto.CargoRequestToCargoConverter;
import com.training.core.mapper.dto.ClientRequestToClientConverter;
import com.training.core.mapper.model.*;
import com.training.core.mapper.dto.OrderRequestToDeliveryConverter;
import com.training.core.service.DeliverySumCalculatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class ConversionConfig {
    private final DeliverySumCalculatingService deliverySumCalculatingService;
    private final DeliveryToOrderResponseConverter deliveryToOrderResponseConverter;
    private final AddressRequestToAddressConverter toAddressConverter;
    private final CargoRequestToCargoConverter toCargoConverter;
    private final ClientRequestToClientConverter toClientConverter;

    @Bean
    ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<>();

        converters.add(new AddressRequestToAddressConverter());
        converters.add(new CargoRequestToCargoConverter());
        converters.add(new ClientRequestToClientConverter());
        converters.add(new OrderRequestToDeliveryConverter(deliverySumCalculatingService,
                toAddressConverter, toCargoConverter, toClientConverter));

        converters.add(new AddressToAddressResponceConverter());
        converters.add(new CargoToCargoResponseConverter());
        converters.add(new ClientToClientResponseConverter());
        converters.add(new DeliveryToOrderResponseConverter());
        converters.add(new PageToOrderPageResponseConverter(deliveryToOrderResponseConverter));

        factory.setConverters(converters);
        return factory;
        }
}
