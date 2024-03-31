package com.example.usersservice.repositories;

import com.example.usersservice.entities.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter,Long> {
List<Waiter> findByIdRestaurant(Long id);
}
