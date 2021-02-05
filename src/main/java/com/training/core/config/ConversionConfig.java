package com.training.core.config;

import com.training.core.dto.request.CargoRequest;
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

    @Bean
    ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<>();

        AddressRequestToAddressConverter toAddressConverter = new AddressRequestToAddressConverter();
        converters.add(toAddressConverter);
        CargoRequestToCargoConverter toCargoConverter = new CargoRequestToCargoConverter();
        converters.add(toCargoConverter);
        ClientRequestToClientConverter toClientConverter = new ClientRequestToClientConverter();
        converters.add(toClientConverter);
        converters.add(new OrderRequestToDeliveryConverter(deliverySumCalculatingService,
                toAddressConverter, toCargoConverter, toClientConverter));

        AddressToAddressResponceConverter toAddressResponceConverter = new AddressToAddressResponceConverter();
        converters.add(toAddressResponceConverter);
        CargoToCargoResponseConverter toCargoResponseConverter = new CargoToCargoResponseConverter();
        converters.add(toCargoConverter);
        ClientToClientResponseConverter toClientResponseConverter = new ClientToClientResponseConverter();
        converters.add(toClientResponseConverter);
        DeliveryToOrderResponseConverter toOrderResponseConverter = new DeliveryToOrderResponseConverter(toAddressResponceConverter,
                toClientResponseConverter, toCargoResponseConverter);
        converters.add(toOrderResponseConverter);
        converters.add(new PageToOrderPageResponseConverter(toOrderResponseConverter));

        factory.setConverters(converters);
        return factory;
        }
}
