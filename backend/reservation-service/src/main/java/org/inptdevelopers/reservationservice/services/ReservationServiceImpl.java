package org.inptdevelopers.reservationservice.services;

import lombok.AllArgsConstructor;
import org.inptdevelopers.reservationservice.entities.Reservation;
import org.inptdevelopers.reservationservice.dto.ReservationDTO;
import org.inptdevelopers.reservationservice.exceptions.ReservationNotFoundException;
import org.inptdevelopers.reservationservice.mappers.ReservationMapper;
import org.inptdevelopers.reservationservice.repositories.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;

    /*
    public List<Reservation> getAllReservations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reservationRepository.findAll(pageable).getContent();
    }
    */
    public List<ReservationDTO> getAllReservationsByRestaurantId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Reservation> reservations = reservationRepository.findByRestaurantId(id, pageable).getContent();
        return reservations.stream()
                .map(reservation -> reservationMapper.fromReservation(reservation))
                .toList();
    }

    @Override
    public List<ReservationDTO> getAllReservationsByWaiterId(Long idWaiter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> reservationsPage = reservationRepository.findByWaiterId(idWaiter, pageable);
        return reservationsPage.map(reservation -> reservationMapper.fromReservation(reservation)).getContent();
    }

    @Override
    public List<ReservationDTO> getAllReservationsByClientId(Long clientId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> reservationsPage = reservationRepository.findByClientId(clientId, pageable);
        return reservationsPage.map(reservation -> reservationMapper.fromReservation(reservation)).getContent();
    }

    public ReservationDTO getReservationById(Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: "+ id));
        return reservationMapper.fromReservation(reservation);
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.fromReservationDTO(reservationDTO);
        Reservation createdReservation = reservationRepository.save(reservation);
        return reservationMapper.fromReservation(createdReservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void chargeReservation(Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: "+ id));

    }

    /*public Reservation updateReservation(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }*/
}
