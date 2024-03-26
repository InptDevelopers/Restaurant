package com.example.usersservice.repositories;

import com.example.usersservice.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

}
