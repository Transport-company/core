package com.training.core.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * An object for trancferring data from a controller to a response
 * about a page of deliveries.
 */
@Data
@JsonDeserialize(builder = DeliveryPageResponse.DeliveryPageResponseBuilder.class)
@Builder(builderClassName = "DeliveryPageResponseBuilder")
public class DeliveryPageResponse {

    /**
     * List of orders.
     */
    @Schema(description = "list of deliveries.")
    private final List<DeliveryResponse> content;

    /**
     * Page size.
     */
    @Schema(description = "page size")
    private final int size;

    /**
     * Page number.
     */
    @Schema(description = "page number")
    private final int number;

    /**
     * Total number of pages.
     */
    @Schema(description = "total number of pages")
    private final int totalPages;

    /**
     * Total number of elements.
     */
    @Schema(description = "total number of elements")
    private final long totalElements;

    @JsonPOJOBuilder(withPrefix = "")
    public static class DeliveryPageResponseBuilder {
    }
}
