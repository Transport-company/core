package com.training.core.controller;

import com.training.core.Urls;
import com.training.core.dto.request.FeedbackRequest;
import com.training.core.dto.response.FeedbackPageResponse;
import com.training.core.dto.response.FeedbackResponse;
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

@Tag(name = "Feedback on delivery")
@RequestMapping(Urls.Feedbacks.FULL)
public interface FeedbackController {
    String ID_PATH_VARIABLE = "/{id}";

    @Operation(summary = "get a page of feedbacks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FeedbackPageResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping
    ResponseEntity<FeedbackPageResponse> getList(
            @Parameter(
                    name = "pageable",
                    description = "parameters of the page. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = Pageable.class))
                    Pageable pageable);

    @Operation(summary = "get a feedback by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FeedbackResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @GetMapping(ID_PATH_VARIABLE)
    ResponseEntity<FeedbackResponse> getById(
            @Parameter(
                    name = "id",
                    description = "id  of the feedback to be obtained. Cannot be null",
                    required = true)
            @PathVariable Long id);

    @Operation(summary = "add a new feedback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "an order created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FeedbackResponse.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PostMapping
    ResponseEntity<FeedbackResponse> create(
            @Parameter(
                    description = "the feedback to add. Cannot be null",
                    required = true,
                    schema = @Schema(implementation = FeedbackRequest.class))
            @Valid @RequestBody FeedbackRequest feedbackRequest);

    @Operation(summary = "update an existing feedback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FeedbackResponse.class))),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error", content = @Content)
    })
    @PutMapping(ID_PATH_VARIABLE)
    ResponseEntity<FeedbackResponse> update(
            @Parameter(
                    name = "id",
                    description = "id of the feedback to be updated. Cannot be null.",
                    required = true)
            @PathVariable Long id,
            @Parameter(
                    description = "the feedback to be updated. Cannot be null.",
                    required = true,
                    schema = @Schema(implementation = FeedbackRequest.class))
            @Valid @RequestBody FeedbackRequest feedbackRequest);

    @Operation(summary = "deletes a feedback")
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
                    description = "id of the feedback to be deleted. Cannot be null",
                    required = true)
            @PathVariable Long id);
}
