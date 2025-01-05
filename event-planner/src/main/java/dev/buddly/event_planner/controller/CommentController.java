package dev.buddly.event_planner.controller;

import dev.buddly.event_planner.dto.request.CommentRequest;
import dev.buddly.event_planner.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add/{eventId}")
    public void addComment(
            @Valid @RequestBody CommentRequest request,
            @PathVariable Integer eventId,
            Authentication authentication
    ) {
        commentService.addComment(request, eventId, authentication);
    }

    @PutMapping("/update/{eventId}/{commentId}")
    public void updateComment(
            @Valid @RequestBody CommentRequest request,
            @PathVariable Integer commentId,
            @PathVariable Integer eventId,
            Authentication authentication
    ) {
        commentService.updateComment(request, commentId, eventId, authentication);
    }

    @DeleteMapping("/delete/{eventId}/{commentId}")
    public void deleteComment(
            @PathVariable Integer commentId,
            @PathVariable Integer eventId,
            Authentication authentication
    ) {
        commentService.deleteComment(commentId,eventId, authentication);
    }
}
