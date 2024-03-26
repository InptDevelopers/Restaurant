package com.example.usersservice.repositories;

import com.example.usersservice.entities.Chef;
import com.example.usersservice.entities.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepository extends JpaRepository<Chef,Long> {

}
