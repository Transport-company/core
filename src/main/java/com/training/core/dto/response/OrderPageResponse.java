package com.training.core.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * An object for trancferring data from a controller to a response
 * about a page of orders.
 */
@Data
@JsonDeserialize(builder = OrderPageResponse.OrderPageResponseBuilder.class)
@Builder(builderClassName = "OrderPageResponseBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class OrderPageResponse {

    /**
     * List of orders.
     */
    @Schema(description = "list of orders.")
    private List<OrderResponse> content;

    /**
     * Page size.
     */
    @Schema(description = "page size")
    private int size;

    /**
     * Page number.
     */
    @Schema(description = "page number")
    private int number;

    /**
     * Total number of pages.
     */
    @Schema(description = "total number of pages")
    private int totalPages;

    /**
     * Total number of elements.
     */
    @Schema(description = "total number of elements")
    private long totalElements;

    @JsonPOJOBuilder(withPrefix = "")
    public static class OrderPageResponseBuilder {
    }
}
