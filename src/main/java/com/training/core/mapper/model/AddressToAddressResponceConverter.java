package com.training.core.mapper.model;

import com.training.core.dto.response.AddressResponse;
import com.training.core.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public class AddressToAddressResponceConverter implements Converter<Address, AddressResponse> {

    @Override
    public AddressResponse convert(Address address) {
        return AddressResponse.builder()
                .region(address.getRegion())
                .city(address.getCity())
                .street(address.getStreet())
                .house(address.getHouse())
                .apartment(address.getApartment())
                .build();
    }

}