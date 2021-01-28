package com.training.core.service;

import com.training.core.exception.NotFoundException;
import com.training.core.model.Address;

import java.util.Optional;

public interface AddressService {

    /**
     * Method for finding a {@link Address address} by its unique identifier.
     *
     * @param id {@link Address address} unique identifier
     * @return {@link Address address} object with unique identifier like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Address address} object
     *                                  with unique identifier like in the argument
     */
    Address getById(Long id);

    /**
     * Method for determining the unique identifier by {@link Address address} object fields
     * (search in the database).
     *
     * @param address {@link Address address} object to seek
     * @return id {@link Address address} unique identifier
     */
    Optional<Long> getIdByAddress(Address address);

    /**
     * Method for saving a {@link Address address}  in a repository.
     *
     * @param address {@link Address address} object to save
     * @return saved {@link Address address} object
     * @throws IllegalArgumentException if an input {@link Address address} object is null
     */
    Address save(Address address);

    /**
     * Method for updating a {@link Address address}.
     *
     * @param id      {@link Address address} unique identifier
     * @param address {@link Address address} object to update
     * @return updated {@link Address address} object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link Address address} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    Address update(Long id, Address address);
}
