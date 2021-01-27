package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * An object for transferring data from a request to a controller about a cargo.
 */
@Data
@Builder
public class CargoRequest {

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

}
