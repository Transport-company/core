package com.training.core.service;

import com.training.core.model.Address;

public interface AddressService {

    /**
     * Method for finding a {@link Address address} by its unique identifier.
     * TODO
     */
    Address getById(Long id);

    /**
     * Method for saving a {@link Address address}  in a repository.
     * TODO
     */
    Address save(Address address);

    /**
     * Method for updating a {@link Address address}.
     * TODO
     */
    Address update(Long id, Address address);
}
