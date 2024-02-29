package org.developpers.tableservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int maxSize;
    private Long restaurantId ;
    @OneToMany(mappedBy = "zone")
    private List<ATable> tables =new ArrayList<>();
}