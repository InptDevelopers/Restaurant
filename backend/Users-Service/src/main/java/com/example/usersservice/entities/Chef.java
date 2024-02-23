package com.example.usersservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor

@Data
public class Chef extends  Employee{

    @ElementCollection
    private List<Long> idcommandes;

}
