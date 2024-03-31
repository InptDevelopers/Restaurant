package org.inptdevelopers.reservationservice.dtos;

import lombok.*;
import org.inptdevelopers.reservationservice.models.Items;
import org.inptdevelopers.reservationservice.models.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreationDTO {
    private int numGuests;
    private LocalDateTime reservationTime;
    private int reservationPrice;

    private List<Long> itemsId;
    private Long clientId;

}
