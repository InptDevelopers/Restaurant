package org.developpers.tableservice.repositories;

import org.developpers.tableservice.entities.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone,Long> {
    Page<Zone> findByRestaurantId(Long restaurantId, Pageable pageable) ;


}
