package org.inptdevelopers.reservationservice.controllers;

import lombok.AllArgsConstructor;
/* import lombok.extern.slf4j.Slf4j; */
import org.inptdevelopers.reservationservice.clients.RestaurantRestClient;
import org.inptdevelopers.reservationservice.clients.TableClient;
import org.inptdevelopers.reservationservice.clients.WaiterClient;
import org.inptdevelopers.reservationservice.dtos.ReservationCreationDTO;
import org.inptdevelopers.reservationservice.dtos.ReservationDTO;
import org.inptdevelopers.reservationservice.entities.Reservation;
import org.inptdevelopers.reservationservice.models.ATable;
import org.inptdevelopers.reservationservice.models.Items;
import org.inptdevelopers.reservationservice.services.ReservationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/api/v1/reservations")
@AllArgsConstructor
@CrossOrigin("*")
/* @Slf4j */
public class ReservationController {
    private final ReservationServiceImpl reservationService;
    private final TableClient tableClient;
    private final WaiterClient waiterClient;
    private final RestaurantRestClient restaurantRestClient;


   /* @GetMapping
    public ResponseEntity<ReservationPageDTO> getAllReservations(
            @RequestParam(required = false) Long restaurantId,
            @RequestParam(required = false) Long waiterId,
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) Boolean isCharged,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ReservationPageDTO reservationPageDTO = reservationService.getReservations(restaurantId, waiterId, clientId, isCharged, page, size);
        return new ResponseEntity<>(reservationPageDTO, HttpStatus.OK);
    }*/
    /*@GetMapping("/restaurant/{idRestaurant}")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsByRestaurantId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long idRestaurant) {
        List<ReservationDTO> reservations = reservationService.getAllReservationsByRestaurantId(idRestaurant,page, size);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
*/
    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsByWaiterId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long idClient) {
        List<ReservationDTO> reservations = reservationService.getAllReservationsByClientId(idClient,page, size);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/waiter/{idWaiter}")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsByClientId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable Long idWaiter) {
        List<ReservationDTO> reservations = reservationService.getAllReservationsByRestaurantId(idWaiter,page, size);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        try {
            ReservationDTO reservationDTO = reservationService.getReservationById(id);
            return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
        } catch (ReservationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) {
        ReservationDTO createdReservationDTO = reservationService.createReservation(reservationRequestDTO);
        return new ResponseEntity<>(createdReservationDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/charge")
    public ResponseEntity<ReservationDTO> chargeReservation(@PathVariable Long id) {
        try {
            ReservationDTO reservation = reservationService.chargeReservation(id);
            return new ResponseEntity<>(reservation, HttpStatus.ACCEPTED);
        } catch (ReservationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
*/
    /*@PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }*/
      @GetMapping("/tables/{id}")
    public List<ATable> getemptytable(@RequestHeader("Authorization") String accessToken, @PathVariable Long id){
        return tableClient.findEmptytables(id,accessToken);

    }
    @GetMapping("/restaurants/{restaurantId}/reservations/{reservationId}")
    public Long getwaiter(@RequestHeader("Authorization") String accessToken, @PathVariable("restaurantId")  Long restaurantId, @PathVariable("reservationId") Long reservationId){
        return waiterClient.getWaiter(restaurantId,reservationId,accessToken);


    }
    @PostMapping("/restaurants/{restaurantId}/zones/{zoneId}")
    public ResponseEntity<Reservation> createreservation(@PathVariable("restaurantId") Long restaurantId, @RequestHeader("Authorization") String accessToken, @PathVariable("zoneId") Long zoneId, @RequestBody ReservationCreationDTO reservationCreationDTO){
        Reservation reservationid=reservationService.reservationcreate(zoneId,accessToken,restaurantId,reservationCreationDTO);
        return new ResponseEntity<>(reservationid,HttpStatus.OK);
    }
    @PostMapping("/items")
    public List<Items> getitems(@RequestBody List<Long> items){
          return restaurantRestClient.getItems(items);
    }

}
