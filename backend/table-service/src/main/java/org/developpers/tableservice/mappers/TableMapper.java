package org.developpers.tableservice.mappers;

import org.developpers.tableservice.dtos.TableDTO;
import org.developpers.tableservice.entities.ATable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TableMapper {
    public TableDTO fromTable(ATable table) {
        TableDTO tableDTO = new TableDTO();
        BeanUtils.copyProperties(table, tableDTO);
        return tableDTO;
    }

    public ATable fromTableDTO(TableDTO tableDTO) {
        ATable table = new ATable();
        BeanUtils.copyProperties(tableDTO, table);
        return table;
    }
}
