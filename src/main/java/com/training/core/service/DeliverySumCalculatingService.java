package com.training.core.service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Calculates a delivery sum.
 */
public interface DeliverySumCalculatingService {

    /**
     * Method for calculating sum of the delivery
     *
     * @param distance to which the cargo must be delivered
     * @param date of calculating
     * @return delivery cost for the client
     */
    BigDecimal getSum(int distance, LocalDate date);

}
