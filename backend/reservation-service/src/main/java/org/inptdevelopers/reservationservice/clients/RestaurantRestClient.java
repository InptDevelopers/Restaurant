package org.inptdevelopers.reservationservice.clients;

import org.inptdevelopers.reservationservice.models.Restaurant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

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
}
