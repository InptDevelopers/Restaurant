package com.example.usersservice.controllers;

import com.example.usersservice.Services.AdminService;
import com.example.usersservice.entities.Admin;
import com.example.usersservice.exceptions.AdminException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("api/v1")
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/admins")
    public ResponseEntity saveAdmin(@RequestBody Admin admin){
        adminService.createAdmin(admin);
        return new ResponseEntity(HttpStatus.CREATED);
    }

@GetMapping("/admins/{id}")
    public ResponseEntity getAdmin(Long id) throws AdminException {
        Admin admin=adminService.getAdmin(id);

        return new ResponseEntity(admin, HttpStatus.OK);
}

}
