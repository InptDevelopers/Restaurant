package org.inptdeveloppers.restaurantservice.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO {
    private Long id;
    private String nom;
    private int prix;
    private Long idmenu;
}
