package com.example.usersservice.entities;

import jakarta.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Role {
    @Id
    private String role;

    public String getRole() {
        return role;
    }

    /*  @Override
    public String toString() {
        return role ;
    }*/
}
