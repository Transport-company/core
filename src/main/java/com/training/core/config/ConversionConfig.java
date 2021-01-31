package com.training.core.config;

import com.training.core.mapper.dto.AddressRequestToAddressConverter;
import com.training.core.mapper.dto.CargoRequestToCargoConverter;
import com.training.core.mapper.dto.ClientRequestToClientConverter;
import com.training.core.mapper.dto.DeliveryRequestToDeliveryConverter;
import com.training.core.mapper.dto.OrderRequestToDeliveryConverter;
import com.training.core.mapper.model.AddressToAddressResponceConverter;
import com.training.core.mapper.model.CargoToCargoResponseConverter;
import com.training.core.mapper.model.ClientToClientResponseConverter;
import com.training.core.mapper.model.DeliveryToDeliveryResponseConverter;
import com.training.core.mapper.model.DeliveryToOrderResponseConverter;
import com.training.core.mapper.model.PageToDeliveryPageResponse;
import com.training.core.mapper.model.PageToOrderPageResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConversionConfig {

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
        converters.add(
                new DeliveryRequestToDeliveryConverter(
                        toAddressConverter,
                        toCargoConverter,
                        toClientConverter));
        converters.add(new OrderRequestToDeliveryConverter(
                toAddressConverter, toCargoConverter, toClientConverter));

        AddressToAddressResponceConverter toAddressResponceConverter =
                new AddressToAddressResponceConverter();
        converters.add(toAddressResponceConverter);
        CargoToCargoResponseConverter toCargoResponseConverter =
                new CargoToCargoResponseConverter();
        converters.add(toCargoResponseConverter);
        ClientToClientResponseConverter toClientResponseConverter =
                new ClientToClientResponseConverter();
        converters.add(toClientResponseConverter);
        DeliveryToDeliveryResponseConverter toDeliveryResponseConverter =
                new DeliveryToDeliveryResponseConverter(
                        toAddressResponceConverter,
                        toCargoResponseConverter,
                        toClientResponseConverter);
        converters.add(toDeliveryResponseConverter);
        converters.add(
                new PageToDeliveryPageResponse(
                        toDeliveryResponseConverter));
        DeliveryToOrderResponseConverter toOrderResponseConverter = new DeliveryToOrderResponseConverter(
                toAddressResponceConverter,
                toClientResponseConverter,
                toCargoResponseConverter);
        converters.add(toOrderResponseConverter);
        converters.add(new PageToOrderPageResponseConverter(toOrderResponseConverter));

        factory.setConverters(converters);

        return factory;
    }

}
