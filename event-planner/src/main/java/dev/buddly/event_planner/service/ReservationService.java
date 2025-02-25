package dev.buddly.event_planner.service;

import com.itextpdf.text.DocumentException;
import dev.buddly.event_planner.dto.ReservationMapper;
import dev.buddly.event_planner.dto.response.IsRegisteredResponse;
import dev.buddly.event_planner.dto.response.ReservationResponse;
import dev.buddly.event_planner.exception.OperationNotPermittedException;
import dev.buddly.event_planner.model.Reservation;
import dev.buddly.event_planner.model.User;
import dev.buddly.event_planner.repo.EventRepository;
import dev.buddly.event_planner.repo.ReservationRepository;
import dev.buddly.event_planner.repo.TicketRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final EventRepository eventRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final TicketRepository ticketRepository;
    private final TicketService ticketService;

    public ReservationService(ReservationRepository reservationRepository, EventRepository eventRepository, ReservationMapper reservationMapper, TicketRepository ticketRepository, TicketService ticketService) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
        this.reservationMapper = reservationMapper;
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
    }

    @Transactional
    public String createReservation(Integer eventId, Authentication connectedUser) throws DocumentException, URISyntaxException, IOException {
        User user = (User) connectedUser.getPrincipal();
        var event = eventRepository.findById(eventId).orElseThrow(() -> new OperationNotPermittedException("Event not found"));
        if(event.getCapacity() <= 0){
            throw new OperationNotPermittedException("Event is full");
        }

        var existingReservation = reservationRepository.findByUserAndEvent(user, event);
        if(existingReservation.isPresent()){
            throw new OperationNotPermittedException("User already has a reservation for this event");
        }

        Reservation reservation = new Reservation();
        reservation.setEvent(event);
        reservation.setUser(user);
        reservation.setStatus(null);
        reservation.setCreatedDate(LocalDateTime.now());

        reservationRepository.save(reservation);
        event.setCapacity(event.getCapacity() - 1);
        eventRepository.save(event);

        String ticketFileName = "ticket_" + reservation.getId() + ".pdf";

        return ticketService.uploadFileAndSavePdf(reservation, ticketFileName);
    }

    @Transactional
    public void deleteReservation(Integer eventId, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        var event = eventRepository.findById(eventId).orElseThrow(() -> new OperationNotPermittedException("Event not found"));
        var reservation = reservationRepository.findByUserAndEvent(user, event).orElseThrow(() -> new OperationNotPermittedException("Reservation not found"));
        var ticket = ticketRepository.findByReservation(reservation);
        ticketRepository.delete(ticket);
        reservationRepository.delete(reservation);
        event.setCapacity(event.getCapacity() + 1);
        eventRepository.save(event);
    }

    public List<ReservationResponse> getReservationUser(Integer eventId, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return reservationMapper.toResponse(
                reservations,
                user.fullName(),
                eventRepository.findById(eventId).orElseThrow(() -> new OperationNotPermittedException("Event not found")).getTitle()
        );
    }

    public IsRegisteredResponse isRegistered(Integer eventId, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        var event = eventRepository.findById(eventId).orElseThrow(() -> new OperationNotPermittedException("Event not found"));
        Reservation reservation = reservationRepository.findByUserAndEvent(user, event).orElseThrow(() -> new OperationNotPermittedException("Event not found"));
        var ticket = ticketRepository.findByReservation(reservation);
        if (ticket != null) {
            return new IsRegisteredResponse(
                    true,
                    ticket.getPdfUrl()
            );
        }else {
            return new IsRegisteredResponse(
                    false,
                    null
            );
        }
    }
}
