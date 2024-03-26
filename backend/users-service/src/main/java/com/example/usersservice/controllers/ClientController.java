package com.example.usersservice.controllers;

import com.example.usersservice.entities.Client;
import com.example.usersservice.exceptions.ClientException;
import com.example.usersservice.exceptions.UserException;
import com.example.usersservice.services.ClientService;
import com.example.usersservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
@Transactional
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;
    private final UserService userService;

    // Create a client
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = (Client) userService.createUser(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    // Add reservations to a client
    @PostMapping("/{id}/reservations")
    public ResponseEntity<Client> addReservationsToClient(@RequestBody Long reservations, @PathVariable Long id) {
        try {
            Client client = clientService.getClient(id);
            client.getReservations().add(reservations);
            Client savedClient = (Client) userService.updateUser(client);
            return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
        } catch (ClientException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get reservations of a client
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<Long>> getReservationsOfClient(@PathVariable Long id) {
        try {
            Client client = clientService.getClient(id);
            return new ResponseEntity<>(client.getReservations(), HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/{clientId}/reservations/{reservationId}")
    public ResponseEntity<HttpStatus> deleteReservationOfClient(@PathVariable Long clientId, @PathVariable Long reservationId) {
        try {
            Client client = clientService.getClient(clientId);
            client.getReservations().remove(reservationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ClientException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Get a client by ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        try {
            Client client = clientService.getClient(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // Update a client
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        try {
            Client client = clientService.getClient(id);
            clientDetails.setId(id);
            Client updatedClient = (Client) userService.updateUser(clientDetails);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (ClientException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a client
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserException e) {
            throw new RuntimeException(e);
        }
    }

    // Get clients with pagination
    @GetMapping
    public ResponseEntity<Page<Client>> getAllClients(@RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "5") int size) {
        Page<Client> clients = clientService.getAllClients(page-1, size);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
