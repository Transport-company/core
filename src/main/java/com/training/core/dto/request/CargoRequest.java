package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @Schema(description = "cargo weight", required = true)
    @NotNull(message = "The field is required")
    private final Float weight;

    /**
     * Declared value of the cargo
     */
    @Schema(description = "declared value of the cargo", required = true)
    @NotNull(message = "The field is required")
    private final BigDecimal declaredValue;

    /**
     * Cargo packing length
     */
    @Schema(description = "cargo length", required = true)
    @NotNull(message = "The field is required")
    private final Float length;

    /**
     * Cargo packing width
     */
    @Schema(description = "cargo length", required = true)
    @NotNull(message = "The field is required")
    private final Float width;

    /**
     * Cargo packing height
     */
    @Schema(description = "cargo height", required = true)
    @NotNull(message = "The field is required")
    private final Float height;

}