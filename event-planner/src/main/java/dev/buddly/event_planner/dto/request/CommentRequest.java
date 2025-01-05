package dev.buddly.event_planner.dto.request;

import jakarta.validation.constraints.*;

public record CommentRequest(

        @NotBlank(message = "Content cannot be blank")
        @Size(max = 500, message = "Content cannot exceed 500 characters")
        String content,

        @NotNull(message = "Rating cannot be null")
        @Min(value = 0, message = "Rating must be at least 0")
        @Max(value = 5, message = "Rating must not exceed 5")
        Double raiting
) {
}
