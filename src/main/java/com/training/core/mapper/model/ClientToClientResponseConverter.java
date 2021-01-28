package com.training.core.mapper.model;

import com.training.core.dto.response.ClientResponse;
import com.training.core.model.Client;
import org.springframework.core.convert.converter.Converter;

public class ClientToClientResponseConverter implements Converter<Client, ClientResponse> {

    @Override
    public ClientResponse convert(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .lastName(client.getLastName())
                .firstName(client.getFirstName())
                .middleName(client.getMiddleName())
                .birthday(client.getBirthday())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .created(client.getCreated())
                .updated(client.getUpdated())
                .build();
    }

}