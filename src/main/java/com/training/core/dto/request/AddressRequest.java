package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * An object for transferring data from a request to a controller about an address.
 */
@Data
@Builder
public class AddressRequest {

    /**
     * Subject of the country of sending or delivery
     */
    @Schema(description = "a region", required = true)
    @NotBlank(message = "The field is required")
    @Size(max = 128, message = "Max length is 128")
    private final String region;

    /**
     * City of sending or delivery
     */
    @Schema(description = "a city", required = true)
    @NotBlank(message = "The field is required")
    @Size(max = 128, message = "Max length is 128")
    private final String city;

    /**
     * Street of sending or delivery
     */
    @Schema(description = "a street")
    @Size(max = 255, message = "Max length is 255")
    private final String street;

    /**
     * House number of sending or delivery
     */
    @Schema(description = "a house", required = true)
    @NotBlank(message = "The field is required")
    @Size(max = 16, message = "Max length is 16")
    private final String house;

    /**
     * An apartment of sending or delivery
     */
    @Schema(description = "an apartment")
    @Size(max = 16, message = "Max length is 16")
    private final String apartment;

}
