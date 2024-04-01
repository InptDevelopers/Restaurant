package com.example.usersservice.entities;

import com.example.usersservice.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    @Column(unique = true)
    private String password;
    private String email;
    private String bankAccount;
    @Enumerated(EnumType.STRING)
    private Role role;

}
