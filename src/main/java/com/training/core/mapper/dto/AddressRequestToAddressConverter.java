package com.training.core.mapper.dto;

import com.training.core.dto.request.AddressRequest;
import com.training.core.model.Address;
import org.springframework.core.convert.converter.Converter;

public class AddressRequestToAddressConverter implements Converter<AddressRequest, Address> {
    @Override
    public Address convert(AddressRequest addressRequest) {
        return Address.builder()
                .region(addressRequest.getRegion())
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .house(addressRequest.getHouse())
                .apartment(addressRequest.getApartment())
                .build();
    }
}
