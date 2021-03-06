package com.training.core.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * An object for transferring data from a request to a controller about a client.
 */
@Data
@Builder
public class ClientRequest {

    /**
     * The last name of the client
     */
    @Schema(description = "The last name of the client")
    @Size(max = 50, message = "Max length is 50")
    private final String lastName;

    /**
     * The first name of the client
     */
    @Schema(description = "The first name of the client", required = true)
    @NotBlank(message = "The field is required")
    @Size(max = 50, message = "Max length is 50")
    private final String firstName;

    /**
     * The middle name of the client
     */
    @Schema(description = "The middle name of the client")
    @Size(max = 50, message = "Max length is 50")
    private final String middleName;

    /**
     * Date of birth
     */
    @Schema(description = "Date of birth", required = true)
    @NotNull(message = "The field is required")
    private final LocalDate birthday;

    /**
     * The phone number of the client
     */
    @Schema(description = "The phone number of the client", required = true)
    @NotBlank(message = "The field is required")
    @Size(max = 11, message = "Max length is 11")
    @Pattern(regexp = "\\d{11}")
    private final String phoneNumber;

    /**
     * The email of the client
     */
    @Schema(description = "The email of the clientt", required = true)
    @NotBlank(message = "The field is required")
    @Size(max = 62, message = "Max length is 62")
    @Email(message = "Please provide a valid email address")
    private final String email;

}
