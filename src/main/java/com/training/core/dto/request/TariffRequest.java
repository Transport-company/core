package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
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
    @Schema(description = "the minimum distance (km) accepted for payment", required = true,
            example = "10")
    @NotNull(message = "The field is required")
    @Positive
    private final Integer minDistance;

    /**
     * The distance from which the reduction factor is applied for the price of 1 km.
     */
    @Schema(description =
            "the distance (km) from which the reduction factor is applied for the price of 1 km",
            required = true, example = "1000")
    @NotNull(message = "The field is required")
    @Positive
    private final Integer distanceThreshold;

    /**
     * Reduction factor for the price of 1 km of delivery over the threshold.
     */
    @Schema(description = "reduction factor for the price of 1 km of delivery over the threshold",
            required = true, example = "0.5")
    @NotNull(message = "The field is required")
    private final BigDecimal reductionFactor;

    /**
     * The value of the weight unit for which an additional payment is set.
     */
    @Schema(description =
            "the value of the weight unit (kg) for which an additional payment is set",
            required = true, example = "1")
    @NotNull(message = "The field is required")
    @Positive
    private final Float weightUnit;

    /**
     * The maximum weight value above which the additional payment is set.
     */
    @Schema(description =
            "the maximum weight value (kg) above which the additional payment is set",
            required = true, example = "1")
    @NotNull(message = "The field is required")
    @Positive
    private final Float weightThreshold;

    /**
     * Ratio increase for each additional weight unit.
     */
    @Schema(description = "ratio increase for each additional weight unit",
            required = true, example = "0.1")
    @NotNull(message = "The field is required")
    @PositiveOrZero
    private final Float weightRatioIncrease;

    /**
     * The value of the volume unit for which an additional payment is set.
     */
    @Schema(description =
            "the value of the volume unit (m3) for which an additional payment is set",
            required = true, example = "0.125")
    @NotNull(message = "The field is required")
    @Positive
    private final Float volumeUnit;

    /**
     * The maximum volume value above which the additional payment is set.
     */
    @Schema(description =
            "the maximum volume value (m3) above which the additional payment is set",
            required = true, example = "0.125")
    @NotNull(message = "The field is required")
    @Positive
    private final Float volumeThreashold;

    /**
     * Ratio increase for each additional volume unit.
     */
    @Schema(description = "ratio increase for each additional volume unit",
            required = true, example = "0.1")
    @NotNull(message = "The field is required")
    @PositiveOrZero
    private final Float volumeRatioIncrease;

}
