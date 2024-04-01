package org.inptdevelopers.reservationservice.models;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class ATable {
    private Long id;
    private TableStatus status;
    private List<Long> reservationIds ;

}
