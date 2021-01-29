package com.training.core.service;

import com.training.core.exception.NotFoundException;
import com.training.core.model.Cargo;

public interface CargoService {

    /**
     * Method for finding a {@link Cargo cargo} by its unique identifier.
     *
     * @param id {@link Cargo cargo} unique identifier
     * @return {@link Cargo cargo} object with unique identifier like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Cargo cargo} object
     *                                  with unique identifier like in the argument
     */
    Cargo getById(Long id);

    /**
     * Method for saving a {@link Cargo cargo} in a repository.
     *
     * @param cargo {@link Cargo cargo} object to save
     * @return saved {@link Cargo cargo} object
     * @throws IllegalArgumentException if an input {@link Cargo cargo} object is null
     */
    Cargo save(Cargo cargo);

    /**
     * Method for updating a  {@link Cargo cargo}.
     *
     * @param id    {@link Cargo cargo} unique identifier
     * @param cargo {@link Cargo cargo} object to update
     * @return updated {@link Cargo cargo} object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link Cargo cargo} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    Cargo update(Long id, Cargo cargo);
}
