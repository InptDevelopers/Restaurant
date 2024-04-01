package com.example.usersservice.controllers;

import com.example.usersservice.services.ChefService;
import com.example.usersservice.services.UserService;
import com.example.usersservice.entities.Chef;
import com.example.usersservice.enums.Role;
import com.example.usersservice.exceptions.ChefException;
import com.example.usersservice.exceptions.UserException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chefs")
@AllArgsConstructor
@Transactional
@CrossOrigin("*")

public class ChefController {

    private final ChefService chefService;
    private final UserService userService;

    // Create a chef
    @PostMapping
    public ResponseEntity<Chef> createChef(@RequestBody Chef chef) {
        chef.setRole(Role.CHEF);
        Chef savedChef = (Chef) userService.createUser(chef);
        return new ResponseEntity<>(savedChef, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/commands")
    public ResponseEntity<Chef> addCommandToChef(@RequestBody List<Long> commands, @PathVariable Long id) {
        try {
            Chef chef = chefService.getChef(id);
            commands.forEach(chef::addCommands);
            Chef savedChef = (Chef) userService.createUser(chef);
            return new ResponseEntity<>(savedChef, HttpStatus.CREATED);
        } catch (ChefException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/commands")
    public ResponseEntity<List<Long>> getCommandsChef(@PathVariable Long id) {
        try {
            Chef chef = chefService.getChef(id);
            return new ResponseEntity<>(chef.getCommands(), HttpStatus.CREATED);
        } catch (ChefException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{clientId}/commands/{commandId}")
    public ResponseEntity<HttpStatus> deleteCommandChef(@PathVariable Long clientId, @PathVariable Long commandId) {
        try {
            Chef chef = chefService.getChef(clientId);
            chef.getCommands().remove(commandId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ChefException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get a chef by ID
    @GetMapping("/{id}")
    public ResponseEntity<Chef> getChef(@PathVariable Long id) {
        Chef chef;
        try {
            chef = chefService.getChef(id);
        } catch (ChefException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chef, HttpStatus.OK);
    }

    // Update a chef
    @PutMapping("/{id}")
    public ResponseEntity<Chef> updateChef(@PathVariable Long id, @RequestBody Chef chefDetails) {
        try {
            chefService.getChef(id);
            chefDetails.setId(id);
            Chef updatedChef = (Chef) userService.updateUser(chefDetails);
            return new ResponseEntity<>(updatedChef, HttpStatus.OK);
        } catch (ChefException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a chef
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteChef(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get chefs with pagination
    @GetMapping
    public ResponseEntity<Page<Chef>> getAllChefs(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Chef> chefs = chefService.getAllChefs(page - 1, size);
        return new ResponseEntity<>(chefs, HttpStatus.OK);
    }
}
