package com.training.core.config;

import com.training.core.mapper.dto.AddressRequestToAddressConverter;
import com.training.core.mapper.dto.CargoRequestToCargoConverter;
import com.training.core.mapper.dto.ClientRequestToClientConverter;
import com.training.core.mapper.dto.DeliveryRequestToDeliveryConverter;
import com.training.core.mapper.dto.OrderRequestToDeliveryConverter;
import com.training.core.mapper.dto.PaymentRequestToChequeConverter;
import com.training.core.mapper.dto.TariffRequestToTariffConverter;
import com.training.core.mapper.model.AddressToAddressResponceConverter;
import com.training.core.mapper.model.CargoToCargoResponseConverter;
import com.training.core.mapper.model.ChequePageToPaymentPageResponseConverter;
import com.training.core.mapper.model.ChequeToPaymentResponseConverter;
import com.training.core.mapper.model.ClientToClientResponseConverter;
import com.training.core.mapper.model.DeliveryPageToDeliveryPageResponse;
import com.training.core.mapper.model.DeliveryToDeliveryResponseConverter;
import com.training.core.mapper.model.DeliveryToOrderResponseConverter;
import com.training.core.mapper.model.OrderPageToOrderPageResponseConverter;
import com.training.core.mapper.model.TariffPageToTariffPageResponse;
import com.training.core.mapper.model.TariffToTariffResponseConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConversionConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        AddressRequestToAddressConverter toAddressConverter = new AddressRequestToAddressConverter();
        registry.addConverter(toAddressConverter);
        CargoRequestToCargoConverter toCargoConverter = new CargoRequestToCargoConverter();
        registry.addConverter(toCargoConverter);
        ClientRequestToClientConverter toClientConverter = new ClientRequestToClientConverter();
        registry.addConverter(toClientConverter);
        registry.addConverter(
                new DeliveryRequestToDeliveryConverter(
                        toAddressConverter,
                        toCargoConverter,
                        toClientConverter));
        registry.addConverter(
                new OrderRequestToDeliveryConverter(
                        toAddressConverter,
                        toCargoConverter,
                        toClientConverter));
        registry.addConverter(new TariffRequestToTariffConverter());
        registry.addConverter(new PaymentRequestToChequeConverter());

        AddressToAddressResponceConverter toAddressResponceConverter =
                new AddressToAddressResponceConverter();
        registry.addConverter(toAddressResponceConverter);
        CargoToCargoResponseConverter toCargoResponseConverter =
                new CargoToCargoResponseConverter();
        registry.addConverter(toCargoResponseConverter);
        ClientToClientResponseConverter toClientResponseConverter =
                new ClientToClientResponseConverter();
        registry.addConverter(toClientResponseConverter);
        DeliveryToDeliveryResponseConverter toDeliveryResponseConverter =
                new DeliveryToDeliveryResponseConverter(
                        toAddressResponceConverter,
                        toCargoResponseConverter,
                        toClientResponseConverter);
        registry.addConverter(toDeliveryResponseConverter);
        registry.addConverter(
                new DeliveryPageToDeliveryPageResponse(
                        toDeliveryResponseConverter));
        DeliveryToOrderResponseConverter toOrderResponseConverter =
                new DeliveryToOrderResponseConverter(
                        toAddressResponceConverter,
                        toClientResponseConverter,
                        toCargoResponseConverter);
        registry.addConverter(toOrderResponseConverter);
        registry.addConverter(
                new OrderPageToOrderPageResponseConverter(
                        toOrderResponseConverter));
        TariffToTariffResponseConverter toTariffResponseConverter =
                new TariffToTariffResponseConverter();
        registry.addConverter(toTariffResponseConverter);
        registry.addConverter(
                new TariffPageToTariffPageResponse(
                        toTariffResponseConverter));
        ChequeToPaymentResponseConverter toPaymentResponseConverter =
                new ChequeToPaymentResponseConverter(toDeliveryResponseConverter);
        registry.addConverter(toPaymentResponseConverter);
        registry.addConverter(
                new ChequePageToPaymentPageResponseConverter(
                        toPaymentResponseConverter));
    }
}
