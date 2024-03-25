package org.inptdeveloppers.restaurantservice.services;

import lombok.AllArgsConstructor;
import org.inptdeveloppers.restaurantservice.DTOS.RestaurantDTO;
import org.inptdeveloppers.restaurantservice.DTOS.RestaurantResponceDTO;
import org.inptdeveloppers.restaurantservice.exceptions.RestaurantNotFoundException;
import org.inptdeveloppers.restaurantservice.entities.Restaurant;
import org.inptdeveloppers.restaurantservice.mappers.RestaurantMapper;
import org.inptdeveloppers.restaurantservice.repositories.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private  final RestaurantMapper restaurantMapper;

    public void createRestaurant(Restaurant restaurant) {

        restaurantRepository.save(restaurant);
    }
    public Restaurant getRestaurant(Long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id));
    }

    // Read
    public RestaurantResponceDTO getAllRestaurants(String nom, int page, int size) {
        Page<Restaurant> restaurantsPage;

        if (nom == null) {
            restaurantsPage = restaurantRepository.findAll(PageRequest.of(page, size));
        } else {
            restaurantsPage = restaurantRepository.findByNom(nom, PageRequest.of(page, size));
        }
        List<Restaurant> restaurantList = restaurantsPage.getContent();

        List<RestaurantDTO> restaurantDTOList = restaurantList.stream()
                .map(restaurantMapper::fromrestaurant)  // Corrected this line
                .collect(Collectors.toList());
        RestaurantResponceDTO response=new RestaurantResponceDTO();
        response.setRestaurantsdto(restaurantDTOList);
        response.setCurrentPage(page);
        response.setTotalPages(restaurantsPage.getTotalPages());
        response.setPageSize(restaurantsPage.getSize());

return  response;
    }
    public RestaurantResponceDTO getAllRestaurantsofAdmin(String nom, int page, int size,Long id) {
        Page<Restaurant> restaurantsPage;

        if (nom == null) {
            restaurantsPage = restaurantRepository.findAllByIdOwner(id,PageRequest.of(page, size));
        } else {
            restaurantsPage = restaurantRepository.findByNomAndIdOwner(nom, PageRequest.of(page, size),id);
        }
        List<Restaurant> restaurantList = restaurantsPage.getContent();

        List<RestaurantDTO> restaurantDTOList = restaurantList.stream()
                .map(restaurantMapper::fromrestaurant)  // Corrected this line
                .collect(Collectors.toList());
        RestaurantResponceDTO response=new RestaurantResponceDTO();
        response.setRestaurantsdto(restaurantDTOList);
        response.setCurrentPage(page);
        response.setTotalPages(restaurantsPage.getTotalPages());
        response.setPageSize(restaurantsPage.getSize());

        return  response;
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
