package com.example.usersservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor

@Data
public class Chef extends  Employee{

    @ElementCollection
    private List<Long> commands =new ArrayList<>();
    public void addCommands(Long idCommand) {
        this.commands.add(idCommand);
    }

}


