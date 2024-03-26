package com.example.usersservice.services;

import com.example.usersservice.entities.Chef;
import com.example.usersservice.exceptions.ChefException;
import com.example.usersservice.repositories.ChefRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ChefService {
private final ChefRepository chefRepository;
    public List<Chef> getChefs() {
        return  chefRepository.findAll();
    }
    public Chef getChef(Long id) throws ChefException {
        return chefRepository.findById(id)
                .orElseThrow(() -> new ChefException("Chef with is"+id+" not found"));
    }
    public Page<Chef> getAllChefs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return chefRepository.findAll(pageable);
    }

}

