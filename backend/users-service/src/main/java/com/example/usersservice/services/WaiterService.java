package com.example.usersservice.services;

import com.example.usersservice.entities.Waiter;
import com.example.usersservice.exceptions.WaiterException;
import com.example.usersservice.repositories.WaiterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class WaiterService {
    private final WaiterRepository waiterRepository;
    public List<Waiter> getwaiters(){

        List<Waiter> waiters=waiterRepository.findAll();

        return  waiters;
    }
    public Waiter getWaiter(Long id) throws WaiterException {
        return waiterRepository.findById(id) .orElseThrow(() -> new WaiterException("Waiter with id"+id+" not found"));
    }
    public Page<Waiter> getAllWaiters(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return waiterRepository.findAll(pageable);
    }
}

