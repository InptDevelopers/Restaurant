package com.example.usersservice.repositories;

import com.example.usersservice.entities.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitersRepository  extends JpaRepository<Waiter,Long> {

}
