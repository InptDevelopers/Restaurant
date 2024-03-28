package com.example.usersservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usersservice.entities.Client;
import com.example.usersservice.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
