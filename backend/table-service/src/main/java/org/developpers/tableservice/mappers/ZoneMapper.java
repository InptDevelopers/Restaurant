package org.developpers.tableservice.mappers;

import lombok.AllArgsConstructor;
import org.developpers.tableservice.dtos.ZoneDTO;
import org.developpers.tableservice.entities.Zone;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ZoneMapper {
    private  TableMapper tableMapper ;

    public  ZoneDTO fromZone(Zone zone) {
        ZoneDTO zoneDTO = new ZoneDTO();
        BeanUtils.copyProperties(zone, zoneDTO);

            zoneDTO.setTables( zone.getTables()
                    .stream().map(aTable -> tableMapper.fromTable(aTable) )
                    .collect(Collectors.toList()));


        return zoneDTO;
    }

    public  Zone fromZoneDTO(ZoneDTO zoneDTO) {
        Zone zone = new Zone();
        BeanUtils.copyProperties(zoneDTO, zone);
        return zone;
    }
}
