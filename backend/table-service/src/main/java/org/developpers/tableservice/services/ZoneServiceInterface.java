package org.developpers.tableservice.services;

import org.developpers.tableservice.dtos.TableDTO;
import org.developpers.tableservice.dtos.ZoneDTO;
import org.developpers.tableservice.dtos.ZoneDTO2;
import org.developpers.tableservice.entities.Zone;
import org.developpers.tableservice.exceptions.ZoneIsFull;
import org.developpers.tableservice.exceptions.ZoneNotEmpty;
import org.developpers.tableservice.exceptions.ZoneNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ZoneServiceInterface {
    ZoneDTO addZone(ZoneDTO zoneDTO);
    ZoneDTO getZoneById(Long id) throws ZoneNotFoundException;
    ZoneDTO2 getZone2ById(Long id) throws ZoneNotFoundException;
    void deleteZone(Long id,Boolean forceDelete) throws ZoneNotFoundException, ZoneNotEmpty;
    ZoneDTO updateZone(Long id, ZoneDTO zoneDTO) throws ZoneNotFoundException;
    TableDTO addTableToZone(Long zoneId) throws ZoneNotFoundException, ZoneIsFull;

    Page<ZoneDTO> getAllZonesByRestaurantId(Long restaurantId,int page,int size);
   void deleteAllTablesZone(Long zoneId) throws ZoneNotFoundException;

}
