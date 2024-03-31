package org.developpers.tableservice.services;

import org.developpers.tableservice.dtos.TableDTO;
import org.developpers.tableservice.exceptions.TableNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TableServiceInterface {
    TableDTO getTableById(Long id) throws TableNotFoundException;
    void deleteTable(Long id) throws TableNotFoundException;
    TableDTO updateTable(Long id, TableDTO tableDTO) throws TableNotFoundException;
    Page<TableDTO> getAllTablesInZone(Long zoneId, int page, int size);

    Page<TableDTO> getAllTablesByRestaurantId (Long restaurantId,int page,int size);

    List<TableDTO> getEmptytables(Long restaurantId);



}
