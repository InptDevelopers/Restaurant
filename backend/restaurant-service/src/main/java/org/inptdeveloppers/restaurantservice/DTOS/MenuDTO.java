package org.inptdeveloppers.restaurantservice.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.inptdeveloppers.restaurantservice.entities.Item;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class MenuDTO {
    private Long id;
    private Long idrestaurant;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<ItemDTO> items;
}
