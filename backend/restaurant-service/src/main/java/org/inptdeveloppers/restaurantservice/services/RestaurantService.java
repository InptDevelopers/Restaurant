package org.inptdeveloppers.restaurantservice.services;

import lombok.AllArgsConstructor;
import org.inptdeveloppers.restaurantservice.DTOS.RestaurantDTO;
import org.inptdeveloppers.restaurantservice.exceptions.RestaurantNotFoundException;
import org.inptdeveloppers.restaurantservice.entities.Restaurant;
import org.inptdeveloppers.restaurantservice.repositories.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public void createRestaurant(Restaurant restaurant) {

        restaurantRepository.save(restaurant);
    }
    public Restaurant getRestaurant(Long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));
    }

    // Read
    public List<Restaurant> getAllRestaurants(String nom, int page, int size) {
        Page<Restaurant> restaurantsPage;

        if (nom == null) {
            restaurantsPage = restaurantRepository.findAll(PageRequest.of(page, size));
        } else {
            restaurantsPage = restaurantRepository.findByNom(nom, PageRequest.of(page, size));
        }
        List<Restaurant> restaurantList = restaurantsPage.getContent();
return restaurantList;


    }
    public void updateRestaurant(Long id, Restaurant updatedRestaurant) throws RestaurantNotFoundException {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));

        // Update the fields with non-null values from updatedRestaurant
        if (updatedRestaurant.getNom() != null) {
            existingRestaurant.setNom(updatedRestaurant.getNom());
        }
        if (updatedRestaurant.getDescription() != null) {
            existingRestaurant.setDescription(updatedRestaurant.getDescription());

        }
        if (updatedRestaurant.getAddress() != null) {
            existingRestaurant.setAddress(updatedRestaurant.getAddress());


        }



        restaurantRepository.save(existingRestaurant);
    }


    // Delete
    public void deleteRestaurant(Long id) {
       restaurantRepository.delete( restaurantRepository.findById(id).get());
    }
}