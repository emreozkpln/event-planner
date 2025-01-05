package dev.buddly.event_planner.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(

        @NotBlank(message = "Category name is mandatory")
        @NotNull(message = "Category name is mandatory")
        String categoryName
) {
}
