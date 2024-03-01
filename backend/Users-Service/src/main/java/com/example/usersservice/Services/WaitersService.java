package com.example.usersservice.Services;

import com.example.usersservice.entities.Waiter;
import com.example.usersservice.repositories.WaitersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class WaitersService {
    private final WaitersRepository waitersRepository;
    public List<Waiter> getwaiters(){

        List<Waiter> waiters=waitersRepository.findAll();

        return  waiters;
    }
    public void savewaiter(Waiter waiter){
        waitersRepository.save(waiter);
    }
}

