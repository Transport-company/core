package com.training.core.service.impl;

import com.training.core.exception.ErrorMessages;
import com.training.core.exception.NotFoundException;
import com.training.core.model.Client;
import com.training.core.repository.ClientRepository;
import com.training.core.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Client getById(@NonNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());

        log.info("Requested the client with id: {}", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found the client with id " + id));
    }

    @Transactional(readOnly = true)
    @NonNull
    @Override
    public Optional<Client> getOptionalByEmail(String email) {
        Assert.notNull(email, ErrorMessages.NULL_EMAIL.getErrorMessage());

        log.info("Requested the client with email: {}", email);
        return clientRepository.findByEmail(email);
    }

    @Transactional
    @NonNull
    @Override
    public Client save(@NonNull Client client) {
        Assert.notNull(client, ErrorMessages.NULL_CLIENT_OBJECT.getErrorMessage());

        Client saved = clientRepository.save(client);
        log.info("Saved a new client with id: {}", saved.getId());
        return saved;
    }

    @Transactional
    @Override
    @NonNull
    public Client update(@NonNull Long id, @NonNull Client client) {
        Assert.notNull(id, ErrorMessages.NULL_ID.getErrorMessage());
        Assert.notNull(client, ErrorMessages.NULL_CLIENT_OBJECT.getErrorMessage());

        Client fetched = getById(id);
        client.setId(fetched.getId());
        client.setCreated(fetched.getCreated());
        Client updated = clientRepository.save(client);
        log.info("Updated the client with id: {}", updated.getId());
        return updated;
    }

}
