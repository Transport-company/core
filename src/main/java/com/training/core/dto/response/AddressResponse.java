package com.training.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * An object for trancferring data from a controller to a response about an address.
 */
@Data
@Builder
public class AddressResponse {

    /**
     * Unique identifier of the address
     */
    @Schema(description = "an unique identifier", required = true,
            example = "true")
    @NotNull(message = "The field is required")
    private final Long id;

    /**
     * Subject of the country of sending or delivery
     */
    @Schema(description = "a region")
    @NotNull(message = "The field is required")
    private final String region;

    /**
     * City of sending or delivery
     */
    @Schema(description = "a city")
    @NotNull(message = "The field is required")
    private final String city;

    /**
     * Street of sending or delivery
     */
    @Schema(description = "a street")
    @NotNull(message = "The field is required")
    private final String street;

    /**
     * House number of sending or delivery
     */
    @Schema(description = "a house")
    @NotNull(message = "The field is required")
    private final String house;

    /**
     * An apartment of sending or delivery
     */
    @Schema(description = "an apartment")
    @NotNull(message = "The field is required")
    private final String apartment;

    /**
     * Time of creation
     */
    @Schema(description = "time of creation")
    @NotNull(message = "The field is required")
    private final LocalDateTime created;

    /**
     * Update time
     */
    @Schema(description = "update time")
    @NotNull(message = "The field is required")
    private final LocalDateTime updated;
}