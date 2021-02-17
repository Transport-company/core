package com.training.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

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
    @Schema(description = "an unique identifier")
    private final Long id;

    /**
     * Subject of the country of sending or delivery
     */
    @Schema(description = "a region")
    private final String region;

    /**
     * City of sending or delivery
     */
    @Schema(description = "a city")
    private final String city;

    /**
     * Street of sending or delivery
     */
    @Schema(description = "a street")
    private final String street;

    /**
     * House number of sending or delivery
     */
    @Schema(description = "a house")
    private final String house;

    /**
     * An apartment of sending or delivery
     */
    @Schema(description = "an apartment")
    private final String apartment;

    /**
     * Time of creation
     */
    @Schema(description = "time of creation")
    private final LocalDateTime created;

    /**
     * Update time
     */
    @Schema(description = "update time")
    private final LocalDateTime updated;

}
