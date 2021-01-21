package com.training.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * An object for trancferring data from a controller to a response
 * about a page of orders.
 */
@Data
@Builder
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
}
