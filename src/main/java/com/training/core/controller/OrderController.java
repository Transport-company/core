package com.training.core.controller;

import com.training.core.Urls;
import com.training.core.dto.request.OrderRequest;
import com.training.core.dto.response.OrderPageResponse;
import com.training.core.dto.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "Ordering delivery")
@RequestMapping(Urls.Orders.FULL)
public interface OrderController {
    String ID_PATH_VARIABLE = "/{id}";

    @Operation(summary = "get a page of orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderPageResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping(params = { "page", "size" })
    ResponseEntity<OrderPageResponse> getList(
            @Parameter(
                    name = "pageable",
                    description = "parameters of the page. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = Pageable.class))
                    Pageable pageable);

    @Operation(summary = "get an order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping(ID_PATH_VARIABLE)
    ResponseEntity<OrderResponse> getById(
            @Parameter(
                    name = "id",
                    description = "id  of the order to be obtained. Cannot be null",
                    required = true)
            @PathVariable Long id) throws NotFoundException;

    @Operation(summary = "add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "an order created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PostMapping
    ResponseEntity<OrderResponse> create(
            @Parameter(
                    description = "the order to add. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = OrderRequest.class))
            @Valid @RequestBody OrderRequest orderRequest);

    @Operation(summary = "update an existing order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PutMapping(ID_PATH_VARIABLE)
    ResponseEntity<OrderResponse> update(
            @Parameter(
                    name = "id",
                    description = "id of the order to be updated. Cannot be null.",
                    required = true)
            @PathVariable Long id,
            @Parameter(
                    description = "the order to be updated. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = OrderRequest.class))
            @Valid @RequestBody OrderRequest orderRequest);

    @Operation(summary = "deletes an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @DeleteMapping(ID_PATH_VARIABLE)
    ResponseEntity<String> delete(
            @Parameter(
                    name = "id",
                    description = "id of the order to be deleted. Cannot be null",
                    required = true)
            @PathVariable Long id);
}
