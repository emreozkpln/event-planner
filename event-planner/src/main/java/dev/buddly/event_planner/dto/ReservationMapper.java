package dev.buddly.event_planner.dto;

import dev.buddly.event_planner.dto.response.ReservationResponse;
import dev.buddly.event_planner.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationMapper {

    public List<ReservationResponse> toResponse(List<Reservation> reservation, String fullname, String eventName){
        return reservation.stream()
                .map(res -> new ReservationResponse(
                        res.getId(),
                        fullname,
                        eventName,
                        res.getCreatedDate()))
                .toList();
    }
}
