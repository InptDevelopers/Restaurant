package com.example.usersservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usersservice.entities.Employee;
import com.example.usersservice.entities.AppUser;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}