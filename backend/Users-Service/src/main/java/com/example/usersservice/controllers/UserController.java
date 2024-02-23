package com.example.usersservice.controllers;

import com.example.usersservice.Services.UserService;
import com.example.usersservice.entities.Waiter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {
private final UserService userService;
@GetMapping("/waiters")
    public ResponseEntity<List<Waiter>> getwaiters(){
    List<Waiter> waiters=userService.getwaiters();
    return new ResponseEntity<>(waiters, HttpStatus.OK);
}


}
