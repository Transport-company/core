package com.training.core.mapper.dto;

import com.training.core.dto.request.AddressRequest;
import com.training.core.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public class AddressRequestToAddressConverter implements Converter<AddressRequest, Address> {

    @Override
    public Address convert(AddressRequest addressRequest) {
        Address address = Address.builder()
                .region(addressRequest.getRegion())
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .house(addressRequest.getHouse())
                .apartment(addressRequest.getApartment())
                .build();
        address.setCode(address.hashCode());
        return address;
    }
}
