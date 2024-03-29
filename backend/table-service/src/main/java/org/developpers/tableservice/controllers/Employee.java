package org.developpers.tableservice.controllers;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Data
public abstract class Employee extends AppUser {
    private Long idRestaurant;
}
