package com.training.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * An object for trancferring data from a controller to a response about a tariff.
 */
@Data
@Builder
public class TariffResponse {

    /**
     * Unique identifier of the tariff
     */
    @Schema(description = "an unique identifier",
            example = "1")
    private final Long id;

    /**
     * Effective date of the tariff.
     */
    @Schema(description = "effective date of the tariff", required = true,
            example = "2021-01-01")
    private final LocalDate effectiveDate;

    /**
     * Fixed rate for the reception of a cargo.
     */
    @Schema(description = "fixed rate for the reception of a cargo", required = true,
            example = "100.00")
    private final BigDecimal orderSum;

    /**
     * Fixed rate for delivery of a cargo by courier in a city.
     */
    @Schema(description = "fixed rate for delivery of a cargo by courier in a city",
            required = true, example = "500.00")
    private final BigDecimal courierSum;

    /**
     * Delivery price per 1 km.
     */
    @Schema(description = "delivery price per 1 km", required = true,
            example = "0.50")
    private final BigDecimal distancePrice;

    /**
     * The minimum distance accepted for payment.
     */
    @Schema(description = "the minimum distance accepted for payment", required = true,
            example = "10")
    private final int minDistance;

    /**
     * The distance from which the reduction factor is applied for the price of 1 km.
     */
    @Schema(description =
            "the distance from which the reduction factor is applied for the price of 1 km",
            required = true, example = "1000")
    private final int distanceThreshold;

    /**
     * Reduction factor for the price of 1 km of delivery over the threshold.
     */
    @Schema(description = "reduction factor for the price of 1 km of delivery over the threshold",
            required = true, example = "0.5")
    private final BigDecimal reductionFactor;

    /**
     * Time of object creation
     */
    @Schema(description = "a date of tariff creation (filled in automatically)")
    private final LocalDateTime created;

    /**
     * Update time
     */
    @Schema(description = "a date of tariff update (filled in automatically)")
    private final LocalDateTime updated;

}
