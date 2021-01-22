package com.training.core.controller;

import com.training.core.Urls;
import com.training.core.dto.request.ReturnRequest;
import com.training.core.dto.response.ReturnPageResponse;
import com.training.core.dto.response.ReturnResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Accepting refund and returning to sender")
@RequestMapping(Urls.Returns.FULL)
public interface ReturnController {
    String ID_PATH_VARIABLE = "/{id}";

    @Operation(summary = "get a page of returns")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReturnPageResponse.class)))
    })
    @GetMapping
    ResponseEntity<ReturnPageResponse> getList(
            @Parameter(
                    name = "pageable",
                    description = "parameters of the page. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = Pageable.class))
                    Pageable pageable);

    @Operation(summary = "get a return by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReturnResponse.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content)
    })
    @GetMapping(ID_PATH_VARIABLE)
    ResponseEntity<ReturnResponse> getById(
            @Parameter(
                    name = "id",
                    description = "id  of the return to be obtained. Cannot be null",
                    required = true)
            @PathVariable Long id);

    @Operation(summary = "add a new return")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "a return created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReturnResponse.class)))
    })
    @PostMapping
    ResponseEntity<ReturnResponse> create(
            @Parameter(
                    description = "the return to add. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = ReturnRequest.class))
            @Valid @RequestBody ReturnRequest returnRequest);

    @Operation(summary = "update an existing return")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReturnResponse.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content)
    })
    @PutMapping(ID_PATH_VARIABLE)
    ResponseEntity<ReturnResponse> update(
            @Parameter(
                    name = "id",
                    description = "id of the return to be updated. Cannot be null.",
                    required = true)
            @PathVariable Long id,
            @Parameter(
                    description = "the return to be updated. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = ReturnRequest.class))
            @Valid @RequestBody ReturnRequest returnRequest);

    @Operation(summary = "deletes a return")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content)
    })
    @DeleteMapping(ID_PATH_VARIABLE)
    ResponseEntity<String> delete(
            @Parameter(
                    name = "id",
                    description = "id of the return to be deleted. Cannot be null",
                    required = true)
            @PathVariable Long id);
}
