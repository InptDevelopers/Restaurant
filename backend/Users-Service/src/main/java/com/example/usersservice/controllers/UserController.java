package com.example.usersservice.controllers;

import com.example.usersservice.Services.UserService;
import com.example.usersservice.Services.WaitersService;
import com.example.usersservice.entities.Waiter;
import com.example.usersservice.repositories.WaitersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {
private final UserService userService;
private final WaitersService waitersService;
@GetMapping("/waiters")
    public ResponseEntity<List<Waiter>> getwaiters(){
    List<Waiter> waiters=waitersService.getwaiters();
    return new ResponseEntity<>(waiters, HttpStatus.OK);
}
    @PostMapping("/waiters")
    public ResponseEntity savewaiter(@RequestBody Waiter waiter){
       waitersService.savewaiter(waiter);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
