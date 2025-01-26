package dev.buddly.event_planner.dto.response;

public record IsRegisteredResponse(
    boolean isRegistered,
    String pdfUrl
) {
}
