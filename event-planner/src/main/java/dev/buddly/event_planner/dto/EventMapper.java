package dev.buddly.event_planner.dto;

import dev.buddly.event_planner.dto.request.EventRequest;
import dev.buddly.event_planner.dto.response.EventResponse;
import dev.buddly.event_planner.exception.OperationNotPermittedException;
import dev.buddly.event_planner.model.Category;
import dev.buddly.event_planner.model.Event;
import dev.buddly.event_planner.repo.CategoryRepository;
import dev.buddly.event_planner.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventMapper {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;
    private final CommentMapper commentMapper;

    public EventMapper(CategoryRepository categoryRepository,UserRepository userRepository,CategoryMapper categoryMapper,CommentMapper commentMapper) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.categoryMapper = categoryMapper;
        this.commentMapper = commentMapper;
    }

    public Event toEvent(EventRequest request){
        Event event = new Event();
        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setLocation(request.location());
        event.setDate(request.date());
        event.setCapacity(request.capacity());
        event.setImage(request.image());
        event.setStartDate(request.startDate());
        event.setEndDate(request.endDate());
        event.setCreatedDate(LocalDateTime.now());

        if(request.categoryId() != null){
            Set<Category> categories = request.categoryId().stream()
                    .map(id -> categoryRepository.findById(id).orElseThrow())
                    .collect(Collectors.toSet());
            event.setCategories(categories);
        }

        return event;
    }

    public EventResponse toResponse(Event event){
        String username = userRepository.findById(event.getCreatedBy()).orElseThrow().fullName();
        return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getDate(),
                event.getCapacity(),
                event.getImage(),
                event.getStartDate(),
                event.getEndDate(),
                username,
                event.getCreatedDate(),
                event.getLastModifiedDate(),
                event.getCategories().stream().map(categoryMapper::toCategoryResponse).collect(Collectors.toSet()),
                event.getComments().stream().map((comment)-> commentMapper.toResponse(comment,username)).collect(Collectors.toList())
        );

    }

    public Event toUpdate(EventRequest request,Event event){
        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setLocation(request.location());
        event.setDate(request.date());
        event.setCapacity(request.capacity());
        event.setImage(request.image());
        event.setStartDate(request.startDate());
        event.setEndDate(request.endDate());
        event.setCategories(request.categoryId().stream().map(id -> categoryRepository.findById(id).orElseThrow(
                () -> new OperationNotPermittedException("Category not found")
        )).collect(Collectors.toSet()));
        event.setLastModifiedDate(LocalDateTime.now());
        return event;
    }
}
