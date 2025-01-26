package dev.buddly.event_planner.controller;

import com.itextpdf.text.DocumentException;
import dev.buddly.event_planner.dto.response.IsRegisteredResponse;
import dev.buddly.event_planner.dto.response.ReservationResponse;
import dev.buddly.event_planner.handler.ResponseHandler;
import dev.buddly.event_planner.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create/{eventId}")
    public ResponseEntity<Object> createReservation(
            @PathVariable Integer eventId,
            Authentication connectedUser
    ) throws DocumentException, URISyntaxException, IOException {
        String url = reservationService.createReservation(eventId, connectedUser);
        return ResponseHandler.handle("Reservation created", HttpStatus.CREATED, url);
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<Object> deleteReservation(
            @PathVariable Integer eventId,
            Authentication connectedUser
    ) {
        reservationService.deleteReservation(eventId, connectedUser);
        return ResponseHandler.handle("Reservation deleted", HttpStatus.OK, null);
    }

    @GetMapping("/user/{eventId}")
    public ResponseEntity<List<ReservationResponse>> getReservationUser(
            @PathVariable Integer eventId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(reservationService.getReservationUser(eventId, connectedUser));
    }

    @GetMapping("/isRegistered/{eventId}")
    public ResponseEntity<IsRegisteredResponse> isRegistered(
            @PathVariable Integer eventId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(reservationService.isRegistered(eventId, connectedUser));
    }
}
