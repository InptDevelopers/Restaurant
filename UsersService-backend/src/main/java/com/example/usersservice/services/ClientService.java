package com.example.usersservice.services;

import com.example.usersservice.entities.Client;
import com.example.usersservice.exceptions.ClientException;
import com.example.usersservice.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClientService {
private final ClientRepository clientRepository;
    public List<Client> getclients() {
    return  clientRepository.findAll();
}
    public Client getClient(Long id) throws ClientException {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientException("Client with id"+id+" not found"));
    }
    public Page<Client> getAllClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable);
    }

}

