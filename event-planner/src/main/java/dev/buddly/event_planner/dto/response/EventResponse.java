package dev.buddly.event_planner.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record EventResponse(
        Integer id,
        String title,
        String description,
        String location,
        LocalDateTime date,
        int capacity,
        String image,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String fullname,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        Set<CategoryResponse> categories,
        List<CommentResponse> comments
) {
}
