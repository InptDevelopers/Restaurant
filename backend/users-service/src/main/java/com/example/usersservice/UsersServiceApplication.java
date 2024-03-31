package com.example.usersservice;

import com.example.usersservice.entities.*;
import com.example.usersservice.enums.Role;
import com.example.usersservice.repositories.AdminRepository;
import com.example.usersservice.repositories.EmployeeRepository;
import com.example.usersservice.repositories.RoleRepository;
import com.example.usersservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@AllArgsConstructor
@SpringBootApplication
@Slf4j
@EnableFeignClients

public class UsersServiceApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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

        // testest
        // Chef chef2 = new Chef();
        // chef2.setName("ismail2");
        // chef2.setEmail("ismail2@gmail.com");
        // chef2.addCommands(4L);
        // chef2.addCommands(5L);

        Waiter waiter = new Waiter();
        waiter.setName("ilyass");
        waiter.setEmail("ilyass@gmail.com");
        waiter.getReservations().add(1L);
        waiter.setIdRestaurant(1L);

        waiter.getReservations().add(2L);
        waiter.setPassword(passwordEncoder.encode("123"));
    waiter.setRole(Role.ADMIN);
        Waiter waiter2 = new Waiter();
        waiter2.setName("ilyass");
        waiter2.setEmail("osama@gmail.com");
        waiter2.getReservations().add(1L);
        waiter2.setIdRestaurant(1L);

        waiter2.getReservations().add(2L);
        waiter2.setRole(Role.CLIENT);
        waiter2.setPassword(passwordEncoder.encode("123"));

        // Assuming Employee is a subclass of User
        System.out.println("///////////////////////////////////////////////////////");
        // Set properties for your entities if needed

        // Save entities to the repository
        userRepository.saveAll(List.of(client, waiter,waiter2));
        employeeRepository.saveAll(List.of(chef));
        adminRepository.save(admin);

        List<Employee> employees = employeeRepository.findAll();
        System.out.println(employees.size());

    }
}
