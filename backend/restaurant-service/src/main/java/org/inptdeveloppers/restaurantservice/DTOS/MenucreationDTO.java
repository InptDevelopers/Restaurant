package org.inptdeveloppers.restaurantservice.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenucreationDTO {
    private Long idrestaurant;

    private List<ItemDTO> items;
}
