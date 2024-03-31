package org.inptdevelopers.reservationservice.clients;

import org.inptdevelopers.reservationservice.models.Items;
import org.inptdevelopers.reservationservice.models.Restaurant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "RESTAURANT-SERVICE")
public interface RestaurantRestClient {
    @GetMapping("/restaurants/{id}")
    @CircuitBreaker(name = "restaurantService", fallbackMethod = "getDefaultRestaurant")
    Restaurant findTableById(@PathVariable  Long id);

    default Restaurant getDefaultRestaurant(Long id, Exception exception) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        
        return restaurant;
    }

    @PostMapping("api/v1/restaurants/menu/items")
     List<Items> getItems(@RequestBody List<Long> items);
}
