package org.inptdeveloppers.restaurantservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.inptdeveloppers.restaurantservice.models.Admin;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nom;
    private String description;
    private String address;
    private Long idOwner;

    @Transient
    private Admin admin;

    @ElementCollection
    private List<Long> idZones;
    @ElementCollection
    private List<Long> idReservation;
@OneToOne(mappedBy = "restaurant")
private Menu menu;


}
