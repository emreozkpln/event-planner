package dev.buddly.event_planner.service;

import dev.buddly.event_planner.dto.CommentMapper;
import dev.buddly.event_planner.dto.request.CommentRequest;
import dev.buddly.event_planner.exception.OperationNotPermittedException;
import dev.buddly.event_planner.model.Comment;
import dev.buddly.event_planner.model.User;
import dev.buddly.event_planner.repo.CommentRepository;
import dev.buddly.event_planner.repo.EventRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final EventRepository eventRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, EventRepository eventRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
        this.commentMapper = commentMapper;
    }

    public void addComment(CommentRequest request, Integer eventId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        var event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        Comment comment = commentMapper.toComment(request, user.getId());
        comment.setEvent(event);
        commentRepository.save(comment);
    }

    public void updateComment(CommentRequest request, Integer commentId, Integer eventId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        var event = eventRepository.findById(eventId).orElseThrow(() -> new OperationNotPermittedException("Event not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new OperationNotPermittedException("Comment not found"));
        if (comment.getCreatedBy().equals(user.getId()) || event.getComments().contains(comment)) {
            comment.setContent(request.content());
            comment.setRaiting(request.raiting());
            commentRepository.save(comment);
        }
    }

    public void deleteComment(Integer commentId,Integer eventId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        var event = eventRepository.findById(eventId).orElseThrow(() -> new OperationNotPermittedException("Event not found"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new OperationNotPermittedException("Comment not found"));
        if (comment.getCreatedBy().equals(user.getId()) || event.getComments().contains(comment)) {
            commentRepository.delete(comment);
        }
    }
}
