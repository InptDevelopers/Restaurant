package org.developpers.tableservice.dtos;

import lombok.Data;
import org.developpers.tableservice.enums.TableStatus;

import java.util.List;

@Data
public class TableDTO {
    private Long id;
    private TableStatus status;
    List<Long> reservationsIds;
}
