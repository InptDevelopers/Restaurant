package com.example.usersservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor

@Entity
@Data

public class Waiter extends Employee {

    @ElementCollection
    private List<Long> reservations=new ArrayList<>();
}
