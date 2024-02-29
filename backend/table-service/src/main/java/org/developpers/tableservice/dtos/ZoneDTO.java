package org.developpers.tableservice.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ZoneDTO {
    private Long id;
    private String description;
    private int maxSize;
    private Long restaurantId ;
    private List<TableDTO> tables;
}
