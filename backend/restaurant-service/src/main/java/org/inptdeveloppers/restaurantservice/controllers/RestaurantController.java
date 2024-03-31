package org.inptdeveloppers.restaurantservice.controllers;

import lombok.AllArgsConstructor;
import org.inptdeveloppers.restaurantservice.DTOS.RestaurantDTO;
import org.inptdeveloppers.restaurantservice.DTOS.RestaurantResponceDTO;
import org.inptdeveloppers.restaurantservice.entities.Restaurant;
import org.inptdeveloppers.restaurantservice.exceptions.RestaurantNotFoundException;
import org.inptdeveloppers.restaurantservice.services.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
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
    public ResponseEntity<RestaurantResponceDTO> geRestaurants(@RequestParam(required = false) String nom,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
   RestaurantResponceDTO restaurants=restaurantService.getAllRestaurants(nom,page,size);
    return new ResponseEntity<>(restaurants,HttpStatus.OK);
}
@PostMapping("/restaurants")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
    Restaurant createdRestaurant =restaurantService.createRestaurant(restaurant);
    return new ResponseEntity<>(createdRestaurant,HttpStatus.CREATED);
}

@DeleteMapping("/restaurants/{id}")
    public ResponseEntity deleteRestaurant(@PathVariable Long id){
    restaurantService.deleteRestaurant(id);
    return new ResponseEntity(HttpStatus.OK);
}

    @GetMapping("/restaurants/admin/{id}")
    public ResponseEntity<RestaurantResponceDTO> geRestaurantsAdmin(@RequestParam(required = false) String nom,
                                                               @RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,@PathVariable long id){
        RestaurantResponceDTO restaurants=restaurantService.getAllRestaurantsofAdmin(nom,page,size,id);
        return new ResponseEntity<>(restaurants,HttpStatus.OK);
    }
}
