package com.example.usersservice.Services;

import com.example.usersservice.entities.Client;
import com.example.usersservice.exceptions.Clientexception;
import com.example.usersservice.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClientService {
private final ClientRepository clientRepository;
public void createClient(Client client){
    clientRepository.save(client);
}
public List<Client> getclients() {
    return  clientRepository.findAll();
}
    public Client getClient(Long id) throws Clientexception {
        return clientRepository.findById(id)
                .orElseThrow(() -> new Clientexception("Client not found"));
    }



}

