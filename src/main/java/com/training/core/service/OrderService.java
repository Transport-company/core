package com.training.core.service;

import com.training.core.model.Delivery;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

/**
 * Process of delivery ordering.
 */
public interface OrderService {

    /**
     * Method for getting a page of {@link Delivery delivery}.
     *
     * @param pageable parameters of resuested page
     * @return a page of {@link Delivery delivery}
     */
    Page<Delivery> getList(Pageable pageable);

    /**
     * Method for finding a {@link Delivery delivery} by its id.
     *
     * @param id - {@link Delivery delivery} unique identifier
     * @return {@link Delivery delivery} object with id like in arguments
     * @throws IllegalArgumentException if input id is null
     * @throws NotFoundException if there is no {@link Delivery delivery} object
     *                           with unique identifier
     */
    Delivery getById(@NotNull Long id) throws NotFoundException;

    /**
     * Method for adding new data {@link Delivery delivery} to the DB
     *
     * @param delivery - {@link Delivery delivery} object
     * @return {@link Delivery delivery} object
     * @throws IllegalArgumentException if input {@link Delivery delivery} object is null
     */
    Delivery create(Delivery delivery);


    /**
     * Method for updating a {@link Delivery delivery} in DB
     *
     * @param id - {@link Delivery delivery} unique identifier
     * @param delivery -  {@link Delivery delivery} object to update
     * @return updated {@link Delivery delivery} object object
     * @throws IllegalArgumentException if input id or {@link Delivery delivery} object is null
     * @throws NotFoundException  if there is no {@link Delivery delivery} object
     *      *                     with unique identifier
     */
    Delivery update(Long id, Delivery delivery);

    /**
     * Method to remove an object {@link Delivery delivery} from the DB
     *
     * @param id - {@link Delivery delivery} unique identifier
     * @throws IllegalArgumentException if input id is null
     * @throws NotFoundException if there is no {@link Delivery delivery} object
     *                           with unique identifier
     */
    void delete(Long id);
}
