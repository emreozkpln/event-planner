package dev.buddly.event_planner.dto.response;

public record AuthenticationResponse(
        String token,
        String message
) {
}
