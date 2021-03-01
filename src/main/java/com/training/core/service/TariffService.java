package com.training.core.service;

import com.training.core.exception.NotFoundException;
import com.training.core.model.Tariff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

/**
 * Service for working with {@link Tariff tariff} entity.
 */
public interface TariffService {

    /**
     * Method for getting a page of {@link Tariff tariff}.
     *
     * @param pageable parameters of resuested page
     * @return a page of {@link Tariff tariff}
     */
    Page<Tariff> getList(Pageable pageable);

    /**
     * Method for finding a {@link Tariff tariff} by its unique identifier.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id {@link Tariff tariff} unique identifier
     * @return {@link Tariff tariff} object with unique identifier like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Tariff tariff} object
     *                                  with unique identifier like in the argument
     */
    Tariff getById(Long id);

    /**
     * Method for finding a {@link Tariff tariff} for some date.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param date {@link LocalDate data} unique identifier
     * @return {@link Tariff tariff} object for date like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Tariff tariff} object
     *                                  for date like in the argument
     */
    Tariff getForDate(LocalDate date);

    /**
     * Method for saving a {@link Tariff tariff} in a repository.
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param tariff {@link Tariff tariff} object to save
     * @return saved {@link Tariff tariff} object
     * @throws IllegalArgumentException if an input {@link Tariff tariff} object is null
     */
    Tariff save(Tariff tariff);

    /**
     * Method for updating a {@link Tariff tariff}.
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id     {@link Tariff tariff} unique identifier
     * @param tariff {@link Tariff tariff} object to update
     * @return updated {@link Tariff tariff} object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link Tariff tariff} object
     *                                  with unique identifier like in the argument
     */
    Tariff update(Long id, Tariff tariff);

    /**
     * Method for deleting a {@link Tariff tariff}.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id {@link Tariff tariff} unique identifier
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Tariff tariff} object
     *                                  with unique identifier like in the argument
     */
    void delete(Long id);
}
