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
        User client = new Client();
       // Assuming Client is a subclass of User
        User admin = new Admin();
        // Assuming Admin is a subclass of User
        User chef = new Chef();

        User waiter=new Waiter()
                ;

        // Assuming Employee is a subclass of User

        // Set properties for your entities if needed

        // Save entities to the repository
        userRepository.saveAll(List.of(client, admin, chef,waiter));

    }
}
