package com.training.core.service;

import com.training.core.model.Client;

public interface ClientService {

    /**
     * Method for finding a {@link Client address} by its unique identifier.
     * TODO
     */
    Client getById(Long id);

    /**
     * Method for saving a {@link Client address}  in a repository.
     * TODO
     */
    Client save(Client client);

    /**
     * Method for updating a  {@link Client address}.
     * TODO
     */
    Client update(Long id, Client client);
}
