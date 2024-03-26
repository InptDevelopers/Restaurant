package com.example.usersservice.services;

import com.example.usersservice.entities.Admin;
import com.example.usersservice.exceptions.AdminException;
import com.example.usersservice.repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService  {
    private final AdminRepository adminRepository ;
    public Admin getAdmin(Long id) throws AdminException {
            return adminRepository.findById(id) .orElseThrow(() -> new AdminException("Admin with id "+id+"not found"));
    }

    public Page<Admin> getAllAdmins(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return adminRepository.findAll(pageable);
    }

}
