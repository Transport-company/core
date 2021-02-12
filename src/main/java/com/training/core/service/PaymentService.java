package com.training.core.service;

import com.training.core.model.Cheque;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Registers payment for delivery.
 */
public interface PaymentService {

    /**
     * Method for adding new {@link Cheque cheque} to the DB
     *
     * @param cheque - {@link Cheque cheque} object
     * @return {@link Cheque cheque} object
     * @throws IllegalArgumentException if input {@link Cheque cheque} object is null
     */
    Cheque create(Cheque cheque);

    /**
     * Method for getting a page of {@link Cheque cheque}.
     *
     * @param pageable parameters of requested page
     * @return a page of {@link Cheque cheque}
     */
    Page<Cheque> getList(Pageable pageable);

    /**
     * Method for finding a {@link Cheque cheque} by its id.
     *
     * @param id - {@link Cheque cheque} unique identifier
     * @return {@link Cheque cheque} object with id like in arguments
     * @throws IllegalArgumentException if input id is null
     * @throws NotFoundException        if there is no {@link Cheque cheque} object
     *                                  with unique identifier
     */
    Cheque getById(Long id);
}
