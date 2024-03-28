package com.example.usersservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor

@Data
public class Client extends AppUser{

    @ElementCollection
    private List<Long> reservations=new ArrayList<>();


}
