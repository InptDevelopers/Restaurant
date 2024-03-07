package org.inptdeveloppers.restaurantservice.mappers;

import org.inptdeveloppers.restaurantservice.DTOS.RestaurantDTO;
import org.inptdeveloppers.restaurantservice.entities.Restaurant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RestaurantMapper {
public RestaurantDTO fromrestaurant(Restaurant restaurant){
    RestaurantDTO restaurantDTO=new RestaurantDTO();
    BeanUtils.copyProperties(restaurant,restaurantDTO);
    return restaurantDTO;
}


}
