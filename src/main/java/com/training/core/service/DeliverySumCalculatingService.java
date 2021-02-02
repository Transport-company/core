package com.training.core.service;

import java.math.BigDecimal;

/**
 * Calculates a delivery sum.
 */
public interface DeliverySumCalculatingService {

    /**
     * Method for calculating sum of the delivery
     *
     * @return delivery cost for the client
     */
    BigDecimal getSum();
}
