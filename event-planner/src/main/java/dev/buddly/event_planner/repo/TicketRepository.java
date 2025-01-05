package dev.buddly.event_planner.repo;

import dev.buddly.event_planner.model.Reservation;
import dev.buddly.event_planner.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket findByReservation(Reservation reservation);
}
