package dev.buddly.event_planner.service;

import dev.buddly.event_planner.dto.EventMapper;
import dev.buddly.event_planner.dto.request.EventRequest;
import dev.buddly.event_planner.dto.response.CommentResponse;
import dev.buddly.event_planner.dto.response.EventResponse;
import dev.buddly.event_planner.dto.response.PageResponse;
import dev.buddly.event_planner.exception.OperationNotPermittedException;
import dev.buddly.event_planner.model.Event;
import dev.buddly.event_planner.model.User;
import dev.buddly.event_planner.repo.CommentRepository;
import dev.buddly.event_planner.repo.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public void createEvent(EventRequest request, Authentication connectedUser) {
        var existingEvent = eventRepository.findByTitle(request.title());
        if (existingEvent.isPresent()) {
            throw new OperationNotPermittedException("Event already exists");
        }
        User user = (User) connectedUser.getPrincipal();
        Event event = eventMapper.toEvent(request);
        event.setCreatedBy(user.getId());
        eventRepository.save(event);
    }

    public PageResponse<EventResponse> getAllEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createdDate").descending());
        Page<Event> events = eventRepository.findAll(pageable);
        List<EventResponse> eventResponse = events
                .stream()
                .map(eventMapper::toResponse)
                .toList();

        return new PageResponse<>(
                eventResponse,
                events.getNumber(),
                events.getSize(),
                events.getTotalElements(),
                events.getTotalPages(),
                events.isFirst(),
                events.isLast()
        );
    }

    public void updateEvent(Integer id, EventRequest request, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new OperationNotPermittedException("Event not found")
        );
        if(!Objects.equals(event.getCreatedBy(),user.getId())){
            throw new OperationNotPermittedException("User not authorized to update this event");
        }
        eventRepository.save(eventMapper.toUpdate(request,event));
    }

    public void deleteEvent(Integer id,Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new OperationNotPermittedException("Event not found")
        );
        if (event.getCreatedBy().equals(user.getId())) {
            eventRepository.delete(event);
        }
    }

    public EventResponse getEventById(Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new OperationNotPermittedException("Event not found")
        );
        return eventMapper.toResponse(event);
    }
}
