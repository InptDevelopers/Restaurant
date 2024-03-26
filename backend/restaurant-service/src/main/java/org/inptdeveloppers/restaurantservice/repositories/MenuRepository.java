package org.inptdeveloppers.restaurantservice.repositories;

import org.inptdeveloppers.restaurantservice.entities.Menu;
import org.inptdeveloppers.restaurantservice.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
Menu getByRestaurant(Restaurant restaurant);
}
