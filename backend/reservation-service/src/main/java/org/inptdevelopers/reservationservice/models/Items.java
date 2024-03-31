package org.inptdevelopers.reservationservice.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    private Long id;
    private String nom;
    private int price;
}
