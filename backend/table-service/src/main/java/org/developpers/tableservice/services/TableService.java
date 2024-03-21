package org.developpers.tableservice.services;

import lombok.AllArgsConstructor;
import org.developpers.tableservice.dtos.TableDTO;
import org.developpers.tableservice.entities.ATable;
import org.developpers.tableservice.exceptions.TableNotFoundException;
import org.developpers.tableservice.mappers.TableMapper;
import org.developpers.tableservice.repositories.TableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TableService implements TableServiceInterface {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    @Override
    public TableDTO getTableById(Long id) throws TableNotFoundException{
        ATable table = tableRepository.findById(id)
                .orElseThrow(() -> new TableNotFoundException("Table not found with id: " + id));
        return tableMapper.fromTable(table);
    }

    @Override
    public void deleteTable(Long id) throws TableNotFoundException{
        ATable table = tableRepository.findById(id)
                .orElseThrow(() -> new TableNotFoundException("Table not found with id: " + id));
        tableRepository.delete(table);
    }

    @Override
    public TableDTO updateTable(Long id, TableDTO tableDTO) throws TableNotFoundException{
        ATable existingTable = tableRepository.findById(id)
                .orElseThrow(() -> new TableNotFoundException("Table not found with id: " + id));
        existingTable.setStatus(tableDTO.getStatus());
        ATable updatedTable = tableRepository.save(existingTable);
        return tableMapper.fromTable(updatedTable);
    }

    @Override
    public Page<TableDTO> getAllTablesInZone(Long zoneId, int page, int size){
       /* Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ZoneNotFoundException("Zone not found with id: " + zoneId));
        */
        Pageable pageable = PageRequest.of(page,size) ;
        Page<ATable> tables = tableRepository.findByZoneId(zoneId,pageable);

        return tables.map(tableMapper::fromTable);
    }


}
