package com.training.core.config;

import com.training.core.mapper.model.PageToOrderPageResponseConverter;
import com.training.core.mapper.model.DeliveryToOrderResponseConverter;
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
    @Bean
    ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<>();
        converters.add(new DeliveryToOrderResponseConverter());
        converters.add(new OrderRequestToDeliveryConverter(deliverySumCalculatingService));
        converters.add(new PageToOrderPageResponseConverter(deliveryToOrderResponseConverter));
        factory.setConverters(converters);
        return factory;
        }
}
