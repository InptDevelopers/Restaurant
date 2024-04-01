package org.developpers.tableservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.developpers.tableservice.enums.TableStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ATable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    @ManyToOne
    private Zone zone;
    @ElementCollection
    private List<Long> reservationIds = new ArrayList<>();
}
