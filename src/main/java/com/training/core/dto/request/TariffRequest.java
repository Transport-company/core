package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * An object for transferring data from a request to a controller about a tariff.
 */
@Data
@Builder
public class TariffRequest {

    /**
     * Effective date of the tariff.
     */
    @Schema(description = "effective date of the tariff", required = true,
            example = "2021-01-01")
    @NotNull(message = "The field is required")
    private final LocalDate effectiveDate;

    /**
     * Fixed rate for the reception of a cargo.
     */
    @Schema(description = "fixed rate for the reception of a cargo", required = true,
            example = "100.00")
    @NotNull(message = "The field is required")
    private final BigDecimal orderSum;

    /**
     * Fixed rate for delivery of a cargo by courier in a city.
     */
    @Schema(description = "fixed rate for delivery of a cargo by courier in a city",
            required = true, example = "500.00")
    @NotNull(message = "The field is required")
    private final BigDecimal courierSum;

    /**
     * Delivery price per 1 km.
     */
    @Schema(description = "delivery price per 1 km", required = true,
            example = "0.50")
    @NotNull(message = "The field is required")
    private final BigDecimal distancePrice;

    /**
     * The minimum distance accepted for payment.
     */
    @Schema(description = "the minimum distance accepted for payment", required = true,
            example = "10")
    @NotNull(message = "The field is required")
    private final int minDistance;

    /**
     * The distance from which the reduction factor is applied for the price of 1 km.
     */
    @Schema(description =
            "the distance from which the reduction factor is applied for the price of 1 km",
            required = true, example = "1000")
    @NotNull(message = "The field is required")
    private final int distanceThreshold;

    /**
     * Reduction factor for the price of 1 km of delivery over the threshold.
     */
    @Schema(description = "reduction factor for the price of 1 km of delivery over the threshold",
            required = true, example = "0.5")
    @NotNull(message = "The field is required")
    private final BigDecimal reductionFactor;
}