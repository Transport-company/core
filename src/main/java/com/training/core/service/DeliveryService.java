package com.training.core.service;

import com.training.core.exception.NotFoundException;
import com.training.core.model.Delivery;
import com.training.core.model.DeliveryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service for working with {@link Delivery delivery} entity.
 */
public interface DeliveryService {

    /**
     * Method for getting a page of {@link Delivery delivery}.
     *
     * @param pageable parameters of resuested page
     * @return a page of {@link Delivery delivery}
     */
    Page<Delivery> getList(Pageable pageable);

    /**
     * Method for finding a {@link Delivery delivery} by its unique identifier.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id {@link Delivery delivery} unique identifier
     * @return {@link Delivery delivery} object with unique identifier like in the argument
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Delivery delivery} object
     *                                  with unique identifier like in the argument
     */
    Delivery getById(Long id);

    /**
     * Method for getting a list of {@link Delivery delivery} objects filtered by status.
     *
     * @param status some status of delivery
     * @return a list of {@link Delivery delivery} objects filtered by status
     * @throws IllegalArgumentException if an input status is null
     */
    List<Delivery> getByStatus(DeliveryStatus status);

    /**
     * Method for getting information about payment for {@link Delivery delivery} object
     *
     * @param id {@link Delivery delivery} unique identifier
     * @return information about payment of {@link Delivery delivery} object
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Delivery delivery} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    boolean isPaid(Long id);

    /**
     * Method for getting a {@link Delivery delivery} object by tracking number.
     *
     * @param trackingNumber tracking number of delivery
     * @return a {@link Delivery delivery} object by tracking number
     * @throws IllegalArgumentException if an input status is null
     */
    Delivery getByTrackingNumber(String trackingNumber);

    /**
     * Checks if a traking number already uses in the database.
     *
     * @param trackingNumber a delivery tracking number
     * @return true if the tracking number already exists in the database, in the other case false
     */
    boolean existsTrackingNumber(String trackingNumber);

    /**
     * Method for saving a {@link Delivery delivery} in a repository.
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param delivery {@link Delivery delivery} object to save
     * @return saved {@link Delivery delivery} object
     * @throws IllegalArgumentException if an input {@link Delivery delivery} object is null
     */
    Delivery save(Delivery delivery);

    /**
     * Method for updating a {@link Delivery delivery}.
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id       {@link Delivery delivery} unique identifier
     * @param delivery {@link Delivery delivery} object to update
     * @return updated {@link Delivery delivery} object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link Delivery delivery} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link Delivery delivery} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    Delivery update(Long id, Delivery delivery);

    /**
     * Method for changing a status of {@link Delivery delivery}.
     * Input arguments should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id     unique identifier of {@link Delivery delivery} which change the status
     * @param status a new {@link DeliveryStatus status}
     * @return updated {@link Delivery delivery} object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws NotFoundException        if there is no {@link Delivery delivery} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    Delivery changeStatus(Long id, DeliveryStatus status);

    /**
     * Method for deleting a {@link Delivery delivery}.
     * The input argument should not be null,
     * otherwise will be thrown IllegalArgumentException.
     *
     * @param id {@link Delivery delivery} unique identifier
     * @throws IllegalArgumentException if an input id is null
     * @throws NotFoundException        if there is no {@link Delivery delivery} object
     *                                  with unique identifier like in the argument
     *                                  is not found
     */
    void delete(Long id);
}
