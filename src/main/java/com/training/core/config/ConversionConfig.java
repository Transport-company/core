package com.training.core.config;

import com.training.core.mapper.DeliveryToOrderResponseConverter;
import com.training.core.mapper.OrderRequestToDeliveryConverter;
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
        converters.add(new DeliveryToOrderResponseConverter());
        converters.add(new OrderRequestToDeliveryConverter());
        factory.setConverters(converters);
        return factory;
        }
}
