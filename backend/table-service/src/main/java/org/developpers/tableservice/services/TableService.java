package org.developpers.tableservice.services;

import lombok.AllArgsConstructor;
import org.developpers.tableservice.dtos.TableDTO;
import org.developpers.tableservice.entities.ATable;
import org.developpers.tableservice.entities.Zone;
import org.developpers.tableservice.exceptions.TableNotFoundException;
import org.developpers.tableservice.mappers.TableMapper;
import org.developpers.tableservice.repositories.TableRepository;
import org.developpers.tableservice.repositories.ZoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.developpers.tableservice.enums.TableStatus.NOT_OCCUPIED;

@Service
@AllArgsConstructor
public class TableService implements TableServiceInterface {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    private final ZoneRepository zoneRepository;


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



    @Override
    public Page<TableDTO> getAllTablesByRestaurantId(Long restaurantId, int page, int size) {
        Page<Zone> zones = zoneRepository.findByRestaurantId(restaurantId, PageRequest.of(page, size));
        List<TableDTO> allTables = new ArrayList<>();

        zones.forEach(zone -> {
            Page<TableDTO> tablesInZone = this.getAllTablesInZone(zone.getId(), page, size);
            allTables.addAll(tablesInZone.getContent());
        });

        return new PageImpl<>(allTables, zones.getPageable(), zones.getTotalElements());
    }
    @Override
    public List<TableDTO> getEmptytables(Long zoneId) {


        List<ATable> tables = tableRepository.findByStatusAndZoneId(NOT_OCCUPIED,zoneId);


        List<TableDTO> tableDTOS = new ArrayList<>();

        for (ATable table : tables) {
            tableDTOS.add(tableMapper.fromTable(table));
        }
        return  tableDTOS;
    }




}
