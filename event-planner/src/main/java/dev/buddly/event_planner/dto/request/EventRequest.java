package dev.buddly.event_planner.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Set;

public record EventRequest(
        @NotBlank(message = "Title cannot be empty")
        String title,

        @NotBlank(message = "Description cannot be empty")
        String description,

        @NotBlank(message = "Location cannot be empty")
        String location,

        @NotNull(message = "Date cannot be null")
        @FutureOrPresent(message = "Date must be in the present or future")
        LocalDateTime date,

        @Min(value = 1, message = "Capacity must be greater than 0")
        int capacity,

        @NotBlank(message = "Image URL cannot be empty")
        String image,

        @NotNull(message = "Start date cannot be null")
        @FutureOrPresent(message = "Start date must be in the present or future")
        LocalDateTime startDate,

        @NotNull(message = "End date cannot be null")
        @FutureOrPresent(message = "End date must be in the present or future")
        LocalDateTime endDate,

        @NotNull(message = "Category IDs cannot be null")
        @Size(min = 1, message = "At least one category must be selected")
        Set<@NotNull Integer> categoryId
) {
}
