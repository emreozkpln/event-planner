package dev.buddly.event_planner.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotEmpty(message = "Firstname is mandatory")
        @NotBlank(message = "Firstname is mandatory")
        String firstname,

        @NotEmpty(message = "lastname is mandatory")
        @NotBlank(message = "lastname is mandatory")
        String lastname,

        @Email(message = "Email is not formatted")
        @NotEmpty(message = "email is mandatory")
        @NotBlank(message = "email is mandatory")
        String email,

        @NotEmpty(message = "password is mandatory")
        @NotBlank(message = "password is mandatory")
        @Size(min = 6, message = "Password should be between 6 characters long minimum")
        String password
) {
}
