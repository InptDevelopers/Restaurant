package org.inptdeveloppers.restaurantservice.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantDTO {
    private Long id;
    private String nom;
    private String description;
    private String address;
    private Long idOwner;
}
