package org.inptdevelopers.reservationservice.services;

import lombok.AllArgsConstructor;

import org.inptdevelopers.reservationservice.clients.TableClient;
import org.inptdevelopers.reservationservice.clients.WaiterClient;
import org.inptdevelopers.reservationservice.dtos.ReservationCreationDTO;
import org.inptdevelopers.reservationservice.dtos.ReservationPageDTO;
import org.inptdevelopers.reservationservice.dtos.ReservationRequestDTO;
import org.inptdevelopers.reservationservice.entities.Reservation;
import org.inptdevelopers.reservationservice.dtos.ReservationDTO;
import org.inptdevelopers.reservationservice.exceptions.ReservationNotFoundException;
import org.inptdevelopers.reservationservice.mappers.ReservationMapper;
import org.inptdevelopers.reservationservice.models.ATable;
import org.inptdevelopers.reservationservice.models.TableStatus;
import org.inptdevelopers.reservationservice.repositories.ReservationRepository;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private final TableClient tableClient;
    private final WaiterClient waiterClient;

    public List<Reservation> getAllReservations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reservationRepository.findAll(pageable).getContent();
    }

    @Override
    public List<ReservationDTO> getAllReservationsByClientId(Long clientId, int page, int size, String token) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Reservation> reservationsPage = reservationRepository.findByClientId(clientId, pageable);
        return reservationsPage.map(reservation -> reservationMapper.fromReservation(reservation, token))
                .getContent();
    }

    @Override
    public Reservation reservationcreate(Long zoneId, String token, Long restaurantId,
            ReservationCreationDTO reservationCreationDTO) {
        Reservation reservation;
        reservation = reservationRepository.save(new Reservation());
        reservationMapper.fromReservationcreationDTO(reservation, reservationCreationDTO);
        Long waiterId = waiterClient.getWaiter(restaurantId, reservation.getId(), token);
        List<ATable> tables = tableClient.findEmptytables(zoneId, token);
        Random random = new Random();
        if (tables.size() > 0) {
            ATable table = tables.get(random.nextInt(tables.size()));

            table.setStatus(TableStatus.OCCUPIED);

            reservation.setRestaurantId(restaurantId);
            reservation.setWaiterId(waiterId);

            reservation.setTableId(table.getId());
            Reservation savedReservation = reservationRepository.save(reservation);
            // table.setReservationIds(List.of(savedReservation.getId()));
            tableClient.updateTable(table.getId(), table);
            return reservation;
        }
        return null;

    }

    public List<ATable> reserve(Long zoneId, Long restaurantId, String token) {
        Reservation reservation;
        reservation = reservationRepository.save(new Reservation());
        List<ATable> tables = tableClient.findEmptytables(zoneId, token);

        return tables;
    }
    /*
     * public void deleteReservation(Long id) {
     * reservationRepository.deleteById(id);
     * }
     * 
     * @Override
     * public ReservationDTO chargeReservation(Long id) throws
     * ReservationNotFoundException {
     * Reservation reservation = reservationRepository.findById(id)
     * .orElseThrow(() -> new
     * ReservationNotFoundException("Reservation not found with id: " + id));
     * reservation.setCharged(true);;
     * reservationRepository.save(reservation);
     * return reservationMapper.fromReservation(reservation);
     * }
     */

    /*
     * @Override
     * public ReservationPageDTO getReservations(Long restaurantId, Long waiterId,
     * Long clientId, int page, int size) {
     * Pageable pageable = PageRequest.of(page, size);
     * Page<Reservation> reservationPage;
     * 
     * if (restaurantId != null && waiterId != null && clientId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndWaiterIdAndClientId(restaurantId,
     * waiterId, clientId, pageable);
     * } else if (restaurantId != null && waiterId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndWaiterId(restaurantId, waiterId,
     * pageable);
     * } else if (restaurantId != null && clientId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndClientId(restaurantId, clientId,
     * pageable);
     * } else if (waiterId != null && clientId != null) {
     * reservationPage = reservationRepository.findByWaiterIdAndClientId(waiterId,
     * clientId, pageable);
     * } else if (restaurantId != null) {
     * reservationPage = reservationRepository.findByRestaurantId(restaurantId,
     * pageable);
     * } else if (waiterId != null) {
     * reservationPage = reservationRepository.findByWaiterId(waiterId, pageable);
     * } else if (clientId != null) {
     * reservationPage = reservationRepository.findByClientId(clientId, pageable);
     * } else {
     * throw new
     * IllegalArgumentException("At least one parameter must be provided.");
     * }
     * 
     * List<ReservationDTO> reservationDTOs = reservationPage.getContent().stream()
     * .map(reservation -> reservationMapper.fromReservation(reservation))
     * .collect(Collectors.toList());
     * 
     * return new ReservationPageDTO(reservationDTOs, page, size,
     * reservationPage.getTotalElements());
     * }
     */
    /*
     * @Override
     * public ReservationPageDTO getReservations(Long restaurantId, Long waiterId,
     * Long clientId, Boolean isCharged, int page, int size) {
     * Pageable pageable = PageRequest.of(page, size);
     * Page<Reservation> reservationPage;
     * 
     * if(isCharged != null) {
     * if (restaurantId != null && waiterId != null && clientId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndWaiterIdAndClientIdAndIsCharged(
     * restaurantId, waiterId, clientId, isCharged, pageable);
     * } else if (restaurantId != null && waiterId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndWaiterIdAndIsCharged(restaurantId,
     * waiterId, isCharged, pageable);
     * } else if (restaurantId != null && clientId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndClientIdAndIsCharged(restaurantId,
     * clientId, isCharged, pageable);
     * } else if (waiterId != null && clientId != null) {
     * reservationPage =
     * reservationRepository.findByWaiterIdAndClientIdAndIsCharged(waiterId,
     * clientId, isCharged, pageable);
     * } else if (restaurantId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndIsCharged(restaurantId, isCharged,
     * pageable);
     * } else if (waiterId != null) {
     * reservationPage = reservationRepository.findByWaiterIdAndIsCharged(waiterId,
     * isCharged, pageable);
     * } else if (clientId != null) {
     * reservationPage = reservationRepository.findByClientIdAndIsCharged(clientId,
     * isCharged, pageable);
     * } else {
     * throw new
     * IllegalArgumentException("At least one parameter must be provided.");
     * }
     * } else {
     * if (restaurantId != null && waiterId != null && clientId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndWaiterIdAndClientId(restaurantId,
     * waiterId, clientId, pageable);
     * } else if (restaurantId != null && waiterId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndWaiterId(restaurantId, waiterId,
     * pageable);
     * } else if (restaurantId != null && clientId != null) {
     * reservationPage =
     * reservationRepository.findByRestaurantIdAndClientId(restaurantId, clientId,
     * pageable);
     * } else if (waiterId != null && clientId != null) {
     * reservationPage = reservationRepository.findByWaiterIdAndClientId(waiterId,
     * clientId, pageable);
     * } else if (restaurantId != null) {
     * reservationPage = reservationRepository.findByRestaurantId(restaurantId,
     * pageable);
     * } else if (waiterId != null) {
     * reservationPage = reservationRepository.findByWaiterId(waiterId, pageable);
     * } else if (clientId != null) {
     * reservationPage = reservationRepository.findByClientId(clientId, pageable);
     * } else {
     * throw new
     * IllegalArgumentException("At least one parameter must be provided.");
     * }
     * }
     * 
     * 
     * 
     * List<ReservationDTO> reservationDTOs = reservationPage.getContent().stream()
     * .map(reservation -> reservationMapper.fromReservation(reservation))
     * .collect(Collectors.toList());
     * 
     * return new ReservationPageDTO(reservationDTOs, page, size,
     * reservationPage.getTotalElements());
     * }
     * 
     * 
     * 
     * /*public Reservation updateReservation(Long id, Reservation reservation) {
     * reservation.setId(id);
     * return reservationRepository.save(reservation);
     * }
     */

}
