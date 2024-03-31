package org.inptdevelopers.reservationservice.services;

import org.inptdevelopers.reservationservice.dtos.ReservationCreationDTO;
import org.inptdevelopers.reservationservice.dtos.ReservationPageDTO;
import org.inptdevelopers.reservationservice.dtos.ReservationRequestDTO;
import org.inptdevelopers.reservationservice.dtos.ReservationDTO;
import org.inptdevelopers.reservationservice.entities.Reservation;
import org.inptdevelopers.reservationservice.exceptions.ReservationNotFoundException;
import org.inptdevelopers.reservationservice.models.ATable;

import java.util.List;

public interface ReservationService {
    public List<Reservation> getAllReservations(int page, int size);
  /*  public List<ReservationDTO> getAllReservationsByRestaurantId(Long id, int page, int size);
    public List<ReservationDTO> getAllReservationsByWaiterId(Long idWaiter, int page, int size);*/
    public List<ReservationDTO> getAllReservationsByClientId(Long clientId, int page, int size);
    public ReservationDTO createReservation(ReservationRequestDTO reservationDTO);
    public Reservation reservationcreate(Long id, String token, Long restaurantId, ReservationCreationDTO reservationCreationDTO);
   /* public void deleteReservation(Long id);
    /*
    public ReservationDTO getReservationById(Long id) throws ReservationNotFoundException;
    //public Reservation updateReservation(Long id, Reservation reservation);
    public ReservationDTO chargeReservation(Long id) throws ReservationNotFoundException;
    public ReservationPageDTO getReservations(Long restaurantId, Long waiterId, Long clientId,Boolean isCharged, int page, int size);
*/
}

