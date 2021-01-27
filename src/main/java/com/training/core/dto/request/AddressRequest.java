package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * An object for transferring data from a request to a controller about an address.
 */
@Data
@Builder
public class AddressRequest {

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

}
