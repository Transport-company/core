package com.training.core.service;

import com.training.core.exception.NotFoundException;
import com.training.core.model.Client;

import java.util.Optional;

public interface ClientService {

    /**
     * Method for finding a {@link Client client} by its unique identifier.
     *
     * @param id {@link Client client} unique identifier
     * @return {@link Client client} object with unique identifier like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Client client} object
     *                                  with unique identifier like in the argument
     */
    Client getById(Long id);

    /**
     * Method for geting an optional object of {@link Client client} by its email.
     *
     * @param email {@link Client client} email
     * @return an optional object of {@link Client client} with email like in the argument
     * @throws IllegalArgumentException if an input id is null
     */
    Optional<Client> getOptionalByEmail(String email);

    /**
     * Method for saving a {@link Client client}  in a repository.
     *
     * @param client {@link Client client} object to save
     * @return saved {@link Client client} object
     * @throws IllegalArgumentException if an input {@link Client client} object is null
     */
    Client save(Client client);

    /**
     * Method for updating a  {@link Client client}.
     *
     * @param id     {@link Client client} unique identifier
     * @param client {@link Client client} object to update
     * @return updated {@link Client client} object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link Client client} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    Client update(Long id, Client client);
}
