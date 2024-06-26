package org.inptdevelopers.reservationservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.inptdevelopers.reservationservice.models.ATable;
import org.inptdevelopers.reservationservice.models.Command;
import org.inptdevelopers.reservationservice.models.Items;
import org.inptdevelopers.reservationservice.models.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long id;
    private int numGuests;
    private LocalDateTime reservationTime;
    private int reservationPrice;
private Long tableId;
private List<Items> items;


}
