package com.example.usersservice.Services;

import com.example.usersservice.entities.Admin;
import com.example.usersservice.exceptions.AdminException;
import com.example.usersservice.exceptions.Clientexception;
import com.example.usersservice.repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService  {
    private final AdminRepository adminRepository ;
    public void createAdmin(Admin admin){
        adminRepository.save(admin);
    }

public Admin getAdmin(Long id) throws AdminException {
        return adminRepository.findById(id) .orElseThrow(() -> new AdminException("Admin not found"));

}
}
