package org.inptdeveloppers.restaurantservice.controllers;

import lombok.AllArgsConstructor;
import org.inptdeveloppers.restaurantservice.entities.Restaurant;
import org.inptdeveloppers.restaurantservice.exceptions.RestaurantNotFoundException;
import org.inptdeveloppers.restaurantservice.services.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class RestaurantController {
private final RestaurantService restaurantService;
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Object> getRestaurant(@PathVariable Long id) {
        try {
            Restaurant restaurant = restaurantService.getRestaurant(id);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (RestaurantNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/restaurants/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        try {
            restaurantService.updateRestaurant(id, updatedRestaurant);
            return new ResponseEntity<>("Restaurant updated successfully", HttpStatus.OK);
        } catch (RestaurantNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

@GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> geRestaurants(@RequestParam(required = false) String nom,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size){
    List<Restaurant> restaurants=restaurantService.getAllRestaurants(nom,page,size);
    return new ResponseEntity<>(restaurants,HttpStatus.OK);
}
@PostMapping("/restaurants")
    public ResponseEntity createRestaurant(@RequestBody Restaurant restaurant){
    restaurantService.createRestaurant(restaurant);
    return new ResponseEntity<>(HttpStatus.CREATED);
}
@DeleteMapping("/restaurants/{id}")
    public ResponseEntity deleteRestaurant(@PathVariable Long id){
    restaurantService.deleteRestaurant(id);
    return new ResponseEntity(HttpStatus.OK);
}
}
