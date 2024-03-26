package org.inptdevelopers.reservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.inptdevelopers.reservationservice.models.Command;
import org.inptdevelopers.reservationservice.models.Restaurant;
import org.inptdevelopers.reservationservice.models.Table;
import org.inptdevelopers.reservationservice.models.Waiter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long id;
    private List<Table> tables;
    private Restaurant restaurant;
    private Long clientId;
    private Long waiterId;
    private LocalDateTime reservationTime;
    private int numGuests;
    private Command command;
    private boolean isCharged;
}
