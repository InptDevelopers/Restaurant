package com.example.usersservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Data
@Entity
public abstract class Employee extends AppUser {
    private Long idRestaurant;
}
