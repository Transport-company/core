package com.training.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Tariffs for calculating the sum of delivary.
 */
@Entity
@Table(name = "tariff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tariff {

    /**
     * Unique identifier of the tariff.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Effective date of the tariff.
     */
    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    /**
     * Fixed rate for the reception of a cargo.
     */
    @Column(name = "order_sum")
    private BigDecimal orderSum;

    /**
     * Fixed rate for delivery of a cargo by courier in a city.
     */
    @Column(name = "courier_sum")
    private BigDecimal courierSum;

    /**
     * Delivery price per 1 km.
     */
    @Column(name = "distance_price")
    private BigDecimal distancePrice;

    /**
     * The minimum distance accepted for payment.
     */
    @Column(name = "min_distance")
    private int minDistance;

    /**
     * The distance from which the reduction factor is applied for the price of 1 km.
     */
    @Column(name = "distance_threshold")
    private int distanceThreshold;

    /**
     * Reduction factor for the price of 1 km of delivery over the threshold.
     */
    @Column(name = "reduction_factor")
    private BigDecimal reductionFactor;

    /**
     * The value of the weight unit for which an additional payment is set.
     */
    @Column(name = "weight_unit")
    private float weightUnit;

    /**
     * The maximum weight value above which the additional payment is set.
     */
    @Column(name = "weight_threshold")
    private float weightThreshold;

    /**
     * Ratio increase for each additional weight unit.
     */
    @Column(name = "weight_ratio_increase")
    private float weightRatioIncrease;

    /**
     * The value of the volume unit for which an additional payment is set.
     */
    @Column(name = "volume_unit")
    private float volumeUnit;

    /**
     * The maximum volume value above which the additional payment is set.
     */
    @Column(name = "volume_threshold")
    private float volumeThreshold;

    /**
     * Ratio increase for each additional volume unit.
     */
    @Column(name = "volume_ratio_increase")
    private float volumeRatioIncrease;

    /**
     * Time of object creation.
     */
    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    /**
     * Update time.
     */
    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

}
