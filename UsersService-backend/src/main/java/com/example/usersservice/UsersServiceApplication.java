package com.example.usersservice;

import com.example.usersservice.entities.*;
import com.example.usersservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
@AllArgsConstructor
@SpringBootApplication
public class UsersServiceApplication implements CommandLineRunner{
private final UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        Client client = new Client();
        client.setName("oussama");
        client.setEmail("oussama@gmail.com");
        client.getReservations().add(1L);
        client.getReservations().add(2L);
       // Assuming Client is a subclass of User
        Admin admin = new Admin();
        admin.setName("abderrahmane");
        admin.setEmail("abderrahmane@gmail.com");
        // Assuming Admin is a subclass of User
        Chef chef = new Chef();
        chef.setName("ismail");
        chef.setEmail("ismail@gmail.com");
        chef.addCommands(1L);
        chef.addCommands(2L);
        chef.addCommands(3L);

        //testest
        Chef chef2 = new Chef();
        chef2.setName("ismail2");
        chef2.setEmail("ismail2@gmail.com");
        chef2.addCommands(4L);
        chef2.addCommands(5L);

        Waiter waiter=new Waiter();
        waiter.setName("ilyass");
        waiter.setEmail("ilyass@gmail.com");
        waiter.getReservations().add(1L);
        waiter.getReservations().add(2L);

        // Assuming Employee is a subclass of User

        // Set properties for your entities if needed

        // Save entities to the repository
        userRepository.saveAll(List.of(client, admin, chef,chef2,waiter));

    }
}
