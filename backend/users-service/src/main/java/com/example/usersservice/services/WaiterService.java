package com.example.usersservice.services;

import com.example.usersservice.entities.Waiter;
import com.example.usersservice.exceptions.WaiterException;
import com.example.usersservice.repositories.WaiterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Service
@Transactional
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
    public Long AssignWaiterToReservation(Long restaurantId, Long reservationId){
        List<Waiter> waiters=waiterRepository.findByIdRestaurant(restaurantId);
        for (Waiter waiter:waiters) {
            if(waiter.getReservations().size()<4){
                waiter.getReservations().add(reservationId);
                waiterRepository.save(waiter);
                return  waiter.getId();
            }
        }
        return null;
    }
    public Long AssignWaiter(Long restaurantId){
        List<Waiter> waiters=waiterRepository.findByIdRestaurant(restaurantId);
        for (Waiter waiter:waiters) {
            if(waiter.getReservations().size()<4){

                waiterRepository.save(waiter);
                return  waiter.getId();
            }
        }
        return null;
    }
}

