package org.inptdeveloppers.restaurantservice.repositories;

import org.inptdeveloppers.restaurantservice.entities.Item;
import org.inptdeveloppers.restaurantservice.entities.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Page<Item> findByMenu(Menu menu, Pageable pageable);
}
