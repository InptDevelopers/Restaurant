package org.developpers.tableservice.controllers;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor


@Data

public class Waiter extends Employee {

    @ElementCollection
    private List<Long> reservations=new ArrayList<>();
}
