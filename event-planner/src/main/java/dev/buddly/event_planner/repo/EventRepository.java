package dev.buddly.event_planner.repo;

import dev.buddly.event_planner.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Optional<Event> findByTitle(String title);
}
