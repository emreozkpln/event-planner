package dev.buddly.event_planner.repo;

import dev.buddly.event_planner.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByEventId(Integer eventId);
}
