package org.inptdevelopers.reservationservice.entities;

import jakarta.persistence.*;
import lombok.*;

import org.inptdevelopers.reservationservice.models.ATable;
import org.inptdevelopers.reservationservice.models.Command;
import org.inptdevelopers.reservationservice.models.Items;
import org.inptdevelopers.reservationservice.models.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tableId;
    @Transient
    private ATable table;
    private Long restaurantId;
    @Transient
    private Restaurant restaurant;
    private Long clientId;
    private Long waiterId;
    private LocalDateTime reservationTime;
    private int numGuests;
    @ElementCollection
    private List<Long> itemsId;

private int reservationPrice;
    private boolean isCharged;
}