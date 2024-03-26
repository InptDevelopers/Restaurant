package org.inptdevelopers.reservationservice.repositories;

import org.inptdevelopers.reservationservice.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAll(Pageable pageable);
    Page<Reservation> findByRestaurantId(Long restaurantId, Pageable pageable);
    Page<Reservation> findByWaiterId(Long waiterId, Pageable pageable);
    Page<Reservation> findByClientId(Long clientId, Pageable pageable);
    Page<Reservation> findByRestaurantIdAndWaiterIdAndClientId(Long restaurantId,Long waiterId, Long clientId, Pageable pageable);
    Page<Reservation> findByRestaurantIdAndWaiterId(Long restaurantId,Long waiterId, Pageable pageable);
    Page<Reservation> findByRestaurantIdAndClientId(Long restaurantId, Long clientId, Pageable pageable);
    Page<Reservation> findByWaiterIdAndClientId(Long waiterId, Long clientId, Pageable pageable);

    Page<Reservation> findByRestaurantIdAndIsCharged(Long restaurantId, boolean isCharged, Pageable pageable);
    Page<Reservation> findByWaiterIdAndIsCharged(Long waiterId, boolean isCharged, Pageable pageable);
    Page<Reservation> findByClientIdAndIsCharged(Long clientId, boolean isCharged, Pageable pageable);
    Page<Reservation> findByRestaurantIdAndWaiterIdAndClientIdAndIsCharged(Long restaurantId,Long waiterId, Long clientId, boolean isCharged, Pageable pageable);
    Page<Reservation> findByRestaurantIdAndWaiterIdAndIsCharged(Long restaurantId,Long waiterId, boolean isCharged, Pageable pageable);
    Page<Reservation> findByRestaurantIdAndClientIdAndIsCharged(Long restaurantId, Long clientId, boolean isCharged, Pageable pageable);
    Page<Reservation> findByWaiterIdAndClientIdAndIsCharged(Long waiterId, Long clientId, boolean isCharged, Pageable pageable);


}
