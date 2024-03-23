package org.developpers.tableservice.services;


import lombok.AllArgsConstructor;
import org.developpers.tableservice.dtos.TableDTO;
import org.developpers.tableservice.dtos.ZoneDTO;
import org.developpers.tableservice.entities.ATable;
import org.developpers.tableservice.entities.Zone;
import org.developpers.tableservice.enums.TableStatus;
import org.developpers.tableservice.exceptions.ZoneIsFull;
import org.developpers.tableservice.exceptions.ZoneNotEmpty;
import org.developpers.tableservice.exceptions.ZoneNotFoundException;
import org.developpers.tableservice.mappers.TableMapper;
import org.developpers.tableservice.mappers.ZoneMapper;
import org.developpers.tableservice.repositories.TableRepository;
import org.developpers.tableservice.repositories.ZoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ZoneService implements ZoneServiceInterface {
    private final ZoneRepository zoneRepository;
    private final TableRepository tableRepository;
    private final ZoneMapper zoneMapper;
    private final TableMapper tableMapper;

    @Override
    public ZoneDTO addZone(ZoneDTO zoneDTO) {
        Zone zone = zoneMapper.fromZoneDTO(zoneDTO);
        Zone savedZone = zoneRepository.save(zone);
        return zoneMapper.fromZone(savedZone);
    }

    @Override
    public ZoneDTO getZoneById(Long id) throws ZoneNotFoundException{
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ZoneNotFoundException("Zone not found with id: " + id));
        return zoneMapper.fromZone(zone);
    }

    @Override
    public void deleteZone(Long id,Boolean forceDelete) throws ZoneNotFoundException, ZoneNotEmpty{
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ZoneNotFoundException("Zone not found with id: " + id));


        if(!zone.getTables().isEmpty() && !forceDelete){
            throw new ZoneNotEmpty("Zone not empty with id  "+id);
        }
        deleteAllTablesZone(zone.getId());
        zoneRepository.delete(zone);
    }
    @Override
    public void deleteAllTablesZone(Long id) throws ZoneNotFoundException{
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ZoneNotFoundException("Zone not found with id: " + id));

        List<ATable> tables = zone.getTables();
       tables.forEach(aTable -> tableRepository.deleteById(aTable.getId()));
    }

    @Override
    public ZoneDTO updateZone(Long id, ZoneDTO zoneDTO) throws ZoneNotFoundException{
        Zone existingZone = zoneRepository.findById(id)
                .orElseThrow(() -> new ZoneNotFoundException("Zone not found with id: " + id));
        existingZone.setDescription(zoneDTO.getDescription());
        existingZone.setMaxSize(zoneDTO.getMaxSize());
        Zone updatedZone = zoneRepository.save(existingZone);
        return zoneMapper.fromZone(updatedZone);
    }

    @Override
    public TableDTO addTableToZone(Long zoneId) throws ZoneNotFoundException, ZoneIsFull{
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ZoneNotFoundException("Zone not found with id: " + zoneId));
        if(zone.getTables().size()==zone.getMaxSize()){
           throw new ZoneIsFull("Zone is full with id: " + zoneId) ;
        }
        ATable table = new ATable();
        table.setStatus(TableStatus.NOT_OCCUPIED);
        table.setZone(zone);
        ATable savedTable = tableRepository.save(table);
        return tableMapper.fromTable(savedTable);
    }




    @Override
    public Page<ZoneDTO> getAllZonesByRestaurantId(Long restaurantId,int page , int size) {
        Page<Zone> zones = zoneRepository.findByRestaurantId(restaurantId,PageRequest.of(page,size));
        return zones.map(zoneMapper::fromZone) ;

    }



}
