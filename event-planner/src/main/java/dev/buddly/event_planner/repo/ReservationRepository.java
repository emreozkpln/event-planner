package dev.buddly.event_planner.repo;

import dev.buddly.event_planner.model.Event;
import dev.buddly.event_planner.model.Reservation;
import dev.buddly.event_planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    List<Reservation> findByUser(User user);

    Optional<Reservation> findByUserAndEvent(User user, Event event);
}
