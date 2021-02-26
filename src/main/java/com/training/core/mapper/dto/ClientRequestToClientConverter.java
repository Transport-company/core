package com.training.core.mapper.dto;

import com.training.core.dto.request.ClientRequest;
import com.training.core.model.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientRequestToClientConverter implements Converter<ClientRequest, Client> {

    @Override
    public Client convert(ClientRequest clientRequest) {
        return Client.builder()
                .lastName(clientRequest.getLastName())
                .firstName(clientRequest.getFirstName())
                .middleName(clientRequest.getMiddleName())
                .birthday(clientRequest.getBirthday())
                .phoneNumber(clientRequest.getPhoneNumber())
                .email(clientRequest.getEmail())
                .build();
    }

}
