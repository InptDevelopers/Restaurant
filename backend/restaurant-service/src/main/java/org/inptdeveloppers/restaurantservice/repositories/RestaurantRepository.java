package org.inptdeveloppers.restaurantservice.repositories;

import org.inptdeveloppers.restaurantservice.DTOS.RestaurantDTO;
import org.inptdeveloppers.restaurantservice.entities.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository  extends JpaRepository<Restaurant,Long> {
    Page<Restaurant> findByNom(String Nom,Pageable pageable);
    Page<Restaurant> findAll(Pageable pageable);

}
