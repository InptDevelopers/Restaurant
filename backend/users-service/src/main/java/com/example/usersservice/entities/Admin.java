package com.example.usersservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Entity
@Data
public class Admin extends AppUser{
    private Long idRestaurant;
}
