package com.training.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * An object for trancferring data from a controller to a response about a cargo.
 */
@Data
@Builder
public class CargoResponse {

    /**
     * Unique identifier of the cargo
     */
    @Schema(description = "an unique identifier")
    private final Long id;

    /**
     * Weight of the cargo
     */
    @Schema(description = "cargo weight")
    private final Float weight;

    /**
     * Declared value of the cargo
     */
    @Schema(description = "declared value of the cargo")
    private final BigDecimal declaredValue;

    /**
     * Cargo packing length
     */
    @Schema(description = "cargo length")
    private final Float length;

    /**
     * Cargo packing width
     */
    @Schema(description = "cargo length")
    private final Float width;

    /**
     * Cargo packing height
     */
    @Schema(description = "cargo height")
    private final Float height;

    /**
     * Time of object creation
     */
    @Schema(description = "time of creation")
    private final LocalDateTime created;

    /**
     * Update time
     */
    @Schema(description = "update time")
    private final LocalDateTime updated;
}
