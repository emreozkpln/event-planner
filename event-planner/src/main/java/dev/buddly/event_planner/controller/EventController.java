package dev.buddly.event_planner.controller;

import dev.buddly.event_planner.dto.request.EventRequest;
import dev.buddly.event_planner.dto.response.CommentResponse;
import dev.buddly.event_planner.dto.response.EventResponse;
import dev.buddly.event_planner.dto.response.PageResponse;
import dev.buddly.event_planner.handler.ResponseHandler;
import dev.buddly.event_planner.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<EventResponse>> getAllEvents(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(eventService.getAllEvents(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createEvent(
            @Valid @RequestBody EventRequest request,
            Authentication connectedUser
    ){
        eventService.createEvent(request,connectedUser);
        return ResponseHandler.handle("Event created", HttpStatus.CREATED,null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateEvent(
            @PathVariable Integer id,
            @Valid @RequestBody EventRequest request,
            Authentication connectedUser
    ){
        eventService.updateEvent(id,request,connectedUser);
        return ResponseHandler.handle("Event updated",HttpStatus.OK,null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEvent(
            @PathVariable Integer id,
            Authentication connectedUser
    ){
        eventService.deleteEvent(id,connectedUser);
        return ResponseHandler.handle("Event deleted",HttpStatus.OK,null);
    }
}
