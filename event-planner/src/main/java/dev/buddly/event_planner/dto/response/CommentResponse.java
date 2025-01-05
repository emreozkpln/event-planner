package dev.buddly.event_planner.dto.response;

import java.time.LocalDateTime;

public record CommentResponse(
        Integer id,
        String text,
        Double raiting,
        String fullname,
        LocalDateTime createdDate
) {
}
