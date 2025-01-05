package dev.buddly.event_planner.dto;

import dev.buddly.event_planner.dto.request.CommentRequest;
import dev.buddly.event_planner.dto.response.CommentResponse;
import dev.buddly.event_planner.model.Comment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentMapper {

    public CommentResponse toResponse(Comment comment,String fullname) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getRaiting(),
                fullname,
                comment.getCreatedDate()
        );
    }

    public Comment toComment(CommentRequest request, Integer userId) {
        Comment comment = new Comment();
        comment.setContent(request.content());
        comment.setRaiting(request.raiting());
        comment.setCreatedDate(LocalDateTime.now());
        comment.setCreatedBy(userId);
        return comment;
    }
}
