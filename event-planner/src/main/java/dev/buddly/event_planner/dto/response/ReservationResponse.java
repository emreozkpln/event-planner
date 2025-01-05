package dev.buddly.event_planner.dto.response;

import java.time.LocalDateTime;

public record ReservationResponse(
    Integer id,
    String fullname,
    String eventName,
    LocalDateTime createdDate
) {
}
