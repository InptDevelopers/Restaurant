package org.inptdevelopers.reservationservice.services;

import lombok.AllArgsConstructor;
import org.inptdevelopers.reservationservice.dtos.ReservationPageDTO;
import org.inptdevelopers.reservationservice.dtos.ReservationRequestDTO;
import org.inptdevelopers.reservationservice.entities.Reservation;
import org.inptdevelopers.reservationservice.dtos.ReservationDTO;
import org.inptdevelopers.reservationservice.exceptions.ReservationNotFoundException;
import org.inptdevelopers.reservationservice.mappers.ReservationMapper;
import org.inptdevelopers.reservationservice.repositories.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return reservationsPage.map(reservation -> reservationMapper.fromReservation(reservation))
                .getContent();
    }

    @Override
    public List<ReservationDTO> getAllReservationsByClientId(Long clientId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> reservationsPage = reservationRepository.findByClientId(clientId, pageable);
        return reservationsPage.map(reservation -> reservationMapper.fromReservation(reservation))
                .getContent();
    }

    public ReservationDTO getReservationById(Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: "+ id));
        return reservationMapper.fromReservation(reservation);
    }

    public ReservationDTO createReservation(ReservationRequestDTO reservationDTO) {
        Reservation reservation = reservationMapper.fromReservationRequestDTO(reservationDTO);
        Reservation createdReservation = reservationRepository.save(reservation);
        return reservationMapper.fromReservation(createdReservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationDTO chargeReservation(Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + id));
        reservation.setCharged(true);;
        reservationRepository.save(reservation);
        return reservationMapper.fromReservation(reservation);
    }


    /* @Override
    public ReservationPageDTO getReservations(Long restaurantId, Long waiterId, Long clientId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> reservationPage;

        if (restaurantId != null && waiterId != null && clientId != null) {
            reservationPage = reservationRepository.findByRestaurantIdAndWaiterIdAndClientId(restaurantId, waiterId, clientId, pageable);
        } else if (restaurantId != null && waiterId != null) {
            reservationPage = reservationRepository.findByRestaurantIdAndWaiterId(restaurantId, waiterId, pageable);
        } else if (restaurantId != null && clientId != null) {
            reservationPage = reservationRepository.findByRestaurantIdAndClientId(restaurantId, clientId, pageable);
        } else if (waiterId != null && clientId != null) {
            reservationPage = reservationRepository.findByWaiterIdAndClientId(waiterId, clientId, pageable);
        } else if (restaurantId != null) {
            reservationPage = reservationRepository.findByRestaurantId(restaurantId, pageable);
        } else if (waiterId != null) {
            reservationPage = reservationRepository.findByWaiterId(waiterId, pageable);
        } else if (clientId != null) {
            reservationPage = reservationRepository.findByClientId(clientId, pageable);
        } else {
            throw new IllegalArgumentException("At least one parameter must be provided.");
        }

        List<ReservationDTO> reservationDTOs = reservationPage.getContent().stream()
                .map(reservation -> reservationMapper.fromReservation(reservation))
                .collect(Collectors.toList());

        return new ReservationPageDTO(reservationDTOs, page, size, reservationPage.getTotalElements());
    }
    */

    @Override
    public ReservationPageDTO getReservations(Long restaurantId, Long waiterId, Long clientId, Boolean isCharged, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> reservationPage;

        if(isCharged != null) {
            if (restaurantId != null && waiterId != null && clientId != null) {
                reservationPage = reservationRepository.findByRestaurantIdAndWaiterIdAndClientIdAndIsCharged(restaurantId, waiterId, clientId, isCharged, pageable);
            } else if (restaurantId != null && waiterId != null) {
                reservationPage = reservationRepository.findByRestaurantIdAndWaiterIdAndIsCharged(restaurantId, waiterId, isCharged, pageable);
            } else if (restaurantId != null && clientId != null) {
                reservationPage = reservationRepository.findByRestaurantIdAndClientIdAndIsCharged(restaurantId, clientId, isCharged, pageable);
            } else if (waiterId != null && clientId != null) {
                reservationPage = reservationRepository.findByWaiterIdAndClientIdAndIsCharged(waiterId, clientId, isCharged, pageable);
            } else if (restaurantId != null) {
                reservationPage = reservationRepository.findByRestaurantIdAndIsCharged(restaurantId, isCharged, pageable);
            } else if (waiterId != null) {
                reservationPage = reservationRepository.findByWaiterIdAndIsCharged(waiterId, isCharged, pageable);
            } else if (clientId != null) {
                reservationPage = reservationRepository.findByClientIdAndIsCharged(clientId, isCharged, pageable);
            } else {
                throw new IllegalArgumentException("At least one parameter must be provided.");
            }
        } else {
            if (restaurantId != null && waiterId != null && clientId != null) {
                reservationPage = reservationRepository.findByRestaurantIdAndWaiterIdAndClientId(restaurantId, waiterId, clientId, pageable);
            } else if (restaurantId != null && waiterId != null) {
                reservationPage = reservationRepository.findByRestaurantIdAndWaiterId(restaurantId, waiterId, pageable);
            } else if (restaurantId != null && clientId != null) {
                reservationPage = reservationRepository.findByRestaurantIdAndClientId(restaurantId, clientId, pageable);
            } else if (waiterId != null && clientId != null) {
                reservationPage = reservationRepository.findByWaiterIdAndClientId(waiterId, clientId, pageable);
            } else if (restaurantId != null) {
                reservationPage = reservationRepository.findByRestaurantId(restaurantId, pageable);
            } else if (waiterId != null) {
                reservationPage = reservationRepository.findByWaiterId(waiterId, pageable);
            } else if (clientId != null) {
                reservationPage = reservationRepository.findByClientId(clientId, pageable);
            } else {
                throw new IllegalArgumentException("At least one parameter must be provided.");
            }
        }

        

        List<ReservationDTO> reservationDTOs = reservationPage.getContent().stream()
                .map(reservation -> reservationMapper.fromReservation(reservation))
                .collect(Collectors.toList());

        return new ReservationPageDTO(reservationDTOs, page, size, reservationPage.getTotalElements());
    }



    /*public Reservation updateReservation(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }*/
}
