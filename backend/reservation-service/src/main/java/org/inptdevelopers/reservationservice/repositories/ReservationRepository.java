package org.inptdevelopers.reservationservice.repositories;

import org.inptdevelopers.reservationservice.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAll(Pageable pageable);
    Page<Reservation> findByRestaurantId(Long restaurantId, Pageable pageable);
    Page<Reservation> findByWaiterId(Long waiterId, Pageable pageable);
    Page<Reservation> findByClientId(Long clientId, Pageable pageable);
    Page<Reservation> findByRestaurantIdAndWaiterIdAndClientId(Long restaurantId,Long waiterId, Long clientId, Pageable pageable);
    Page<Reservation> findByRestaurantIdAndWaiterId(Long restaurantId,Long waiterId, Pageable pageable);
    Page<Reservation> findByRestaurantIdAndClientId(Long restaurantId, Long clientId, Pageable pageable);
    Page<Reservation> findByWaiterIdAndClientId(Long waiterId, Long clientId, Pageable pageable);

}
