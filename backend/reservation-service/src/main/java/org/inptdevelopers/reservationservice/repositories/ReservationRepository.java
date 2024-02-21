package org.inptdevelopers.reservationservice.repositories;

import org.inptdevelopers.reservationservice.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
