package org.inptdeveloppers.restaurantservice.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.inptdeveloppers.restaurantservice.entities.Restaurant;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantResponceDTO {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<Restaurant>  restaurants;
}
