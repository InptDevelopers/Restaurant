package org.inptdevelopers.reservationservice.dtos;

import lombok.*;
import org.inptdevelopers.reservationservice.dto.ReservationDTO;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationPageDTO {
    private List<ReservationDTO> reservations;
    private int page;
    private int size;
    private long totalElements;

}

