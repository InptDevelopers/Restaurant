package com.example.usersservice.controllers;

import com.example.usersservice.services.AdminService;
import com.example.usersservice.services.UserService;
import com.example.usersservice.entities.Admin;
import com.example.usersservice.exceptions.AdminException;
import com.example.usersservice.exceptions.UserException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admins")
@AllArgsConstructor
@Transactional
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = (Admin) userService.createUser(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    // Get an admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable Long id) {
        Admin admin;
        try {
            admin = adminService.getAdmin(id);
        } catch (AdminException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    // Update an admin
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        try {
            adminService.getAdmin(id);
        } catch (AdminException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        adminDetails.setId(id);

        Admin updatedAdmin = (Admin) userService.updateUser(adminDetails);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    // Delete an admin
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Admin>> getAllAdmins(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Page<Admin> admins = adminService.getAllAdmins(page, size);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}
