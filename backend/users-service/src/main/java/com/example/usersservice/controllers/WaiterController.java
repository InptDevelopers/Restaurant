package com.example.usersservice.controllers;

import com.example.usersservice.entities.Waiter;
import com.example.usersservice.enums.Role;
import com.example.usersservice.exceptions.UserException;
import com.example.usersservice.exceptions.WaiterException;
import com.example.usersservice.services.WaiterService;
import com.example.usersservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/waiters")
@AllArgsConstructor
@Transactional
/*
 * @CrossOrigin("*")
 */
public class WaiterController {

    private final WaiterService waiterService;
    private final UserService userService;

    // Create a waiter
    @PostMapping
    public ResponseEntity<Waiter> createWaiter(@RequestBody Waiter waiter) {
        waiter.setRole(Role.WAITER);
        Waiter savedWaiter = (Waiter) userService.createUser(waiter);
        return new ResponseEntity<>(savedWaiter, HttpStatus.CREATED);
    }

    // Add reservations to a waiter
    @PostMapping("/{id}/reservations")
    public ResponseEntity<Waiter> addReservationsToWaiter(@RequestBody Long reservationId, @PathVariable Long id) {
        try {
            Waiter waiter = waiterService.getWaiter(id);
            waiter.getReservations().add(reservationId);
            Waiter savedWaiter = (Waiter) userService.updateUser(waiter);
            return new ResponseEntity<>(savedWaiter, HttpStatus.CREATED);
        } catch (WaiterException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get reservations of a waiter
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<Long>> getReservationsOfWaiter(@PathVariable Long id) {
        try {
            Waiter waiter = waiterService.getWaiter(id);
            return new ResponseEntity<>(waiter.getReservations(), HttpStatus.OK);
        } catch (WaiterException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{waiterId}/reservations/{reservationId}")
    public ResponseEntity<HttpStatus> deleteReservationOfWaiter(@PathVariable Long waiterId,
            @PathVariable Long reservationId) {
        try {
            Waiter waiter = waiterService.getWaiter(waiterId);
            waiter.getReservations().remove(reservationId);
            userService.updateUser(waiter);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (WaiterException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get a waiter by ID
    @GetMapping("/{id}")
    public ResponseEntity<Waiter> getWaiter(@PathVariable Long id) {
        try {
            Waiter waiter = waiterService.getWaiter(id);
            return new ResponseEntity<>(waiter, HttpStatus.OK);
        } catch (WaiterException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a waiter
    @PutMapping("/{id}")
    public ResponseEntity<Waiter> updateWaiter(@PathVariable Long id, @RequestBody Waiter waiterDetails) {
        try {
            Waiter waiter = waiterService.getWaiter(id);
            waiterDetails.setId(id);
            Waiter updatedWaiter = (Waiter) userService.updateUser(waiterDetails);
            return new ResponseEntity<>(updatedWaiter, HttpStatus.OK);
        } catch (WaiterException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a waiter
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWaiter(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get waiters with pagination
    @GetMapping
    public ResponseEntity<Page<Waiter>> getAllWaiters(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Waiter> waiters = waiterService.getAllWaiters(page - 1, size);
        return new ResponseEntity<>(waiters, HttpStatus.OK);
    }

    @GetMapping("/available/{restaurantId}/reservations/{reservationId}")
    public Long AssignWaiterToReservation(@PathVariable Long reservationId, @PathVariable Long restaurantId) {
        return waiterService.AssignWaiterToReservation(restaurantId, reservationId);
    }

    @GetMapping("/available/{restaurantId}")
    public Long AssignWaiter(@PathVariable Long restaurantId) {
        return waiterService.AssignWaiter(restaurantId);
    }

}
