package com.training.core.service;

import com.training.core.model.Cargo;

public interface CargoService {

    /**
     * Method for finding a {@link Cargo address} by its unique identifier.
     * TODO
     */
    Cargo getById(Long id);

    /**
     * Method for saving a {@link Cargo address} in a repository.
     * TODO
     */
    Cargo save(Cargo cargo);

    /**
     * Method for updating a  {@link Cargo address}.
     * TODO
     */
    Cargo update(Long id, Cargo cargo);
}
