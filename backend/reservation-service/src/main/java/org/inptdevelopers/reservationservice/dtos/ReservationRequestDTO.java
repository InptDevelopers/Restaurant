package org.inptdevelopers.reservationservice.dtos;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDTO {
    private Long id;
    private List<Long> tableIds;
    private Long restaurantId;
    private Long clientId;
    private Long waiterId;
    private LocalDateTime reservationTime;
    private int numGuests;
    private Long commandId;
    private boolean isCharged;
}
