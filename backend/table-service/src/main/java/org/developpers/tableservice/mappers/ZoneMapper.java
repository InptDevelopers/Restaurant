package org.developpers.tableservice.mappers;

import lombok.AllArgsConstructor;
import org.developpers.tableservice.dtos.ZoneDTO;
import org.developpers.tableservice.dtos.ZoneDTO2;
import org.developpers.tableservice.entities.ATable;
import org.developpers.tableservice.entities.Zone;
import org.developpers.tableservice.enums.TableStatus;
import org.developpers.tableservice.repositories.TableRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ZoneMapper {
    private  TableMapper tableMapper ;
    private final TableRepository tableRepository;

    public  ZoneDTO fromZone(Zone zone) {
        ZoneDTO zoneDTO = new ZoneDTO();
        BeanUtils.copyProperties(zone, zoneDTO);

            zoneDTO.setTables( zone.getTables()
                    .stream().map(aTable -> tableMapper.fromTable(aTable) )
                    .collect(Collectors.toList()));


        return zoneDTO;
    }
    public ZoneDTO2 fromZone2(Zone zone) {
        ZoneDTO2 zoneDTO2 = new ZoneDTO2();
        BeanUtils.copyProperties(zone, zoneDTO2);
        List<ATable> tables=tableRepository.findByStatusAndZoneId(TableStatus.NOT_OCCUPIED,zone.getId());
        zoneDTO2.setEmptytables(tables.size());
return zoneDTO2;




    }

    public  Zone fromZoneDTO(ZoneDTO zoneDTO) {
        Zone zone = new Zone();
        BeanUtils.copyProperties(zoneDTO, zone);
        return zone;
    }
}
