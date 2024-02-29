package org.developpers.tableservice.controllers;

import lombok.AllArgsConstructor;
import org.developpers.tableservice.dtos.TableDTO;
import org.developpers.tableservice.dtos.ZoneDTO;
import org.developpers.tableservice.entities.Zone;
import org.developpers.tableservice.exceptions.ZoneIsFull;
import org.developpers.tableservice.exceptions.ZoneNotEmpty;
import org.developpers.tableservice.exceptions.ZoneNotFoundException;
import org.developpers.tableservice.services.TableService;
import org.developpers.tableservice.services.ZoneService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/zones")
@AllArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;
    private final TableService tableService;

    @PostMapping
    public ResponseEntity<ZoneDTO> addZone(@RequestBody ZoneDTO zoneDTO) {
        ZoneDTO addedZone = zoneService.addZone(zoneDTO);
        return new ResponseEntity<>(addedZone, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getZoneById(@PathVariable Long id) {
        try {
            ZoneDTO zoneDTO = zoneService.getZoneById(id);
            return ResponseEntity.ok(zoneDTO);
        } catch (ZoneNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteZone(@PathVariable Long id) {
        try {
            zoneService.deleteZone(id);
            return ResponseEntity.noContent().build();
        } catch (ZoneNotFoundException | ZoneNotEmpty e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateZone(@PathVariable Long id, @RequestBody ZoneDTO zoneDTO) {
        try {
            ZoneDTO updatedZone = zoneService.updateZone(id, zoneDTO);
            return ResponseEntity.ok(updatedZone);
        } catch (ZoneNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{zoneId}/tables")
    public ResponseEntity addTableToZone(@PathVariable Long zoneId, @RequestBody TableDTO tableDTO) {
        try {
            TableDTO addedTable = zoneService.addTableToZone(zoneId, tableDTO);
            return new ResponseEntity(addedTable, HttpStatus.CREATED);
        } catch (ZoneNotFoundException | ZoneIsFull e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{zoneId}/tables")
    public ResponseEntity getAllTablesInZone(@PathVariable Long zoneId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
            Page<TableDTO> tables = tableService.getAllTablesInZone(zoneId,page,size);
            return ResponseEntity.ok(tables);

    }

    @GetMapping("/")
    public ResponseEntity<Page<ZoneDTO>> getAllZonesByRestaurantId(
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size
                                                                   ,  @RequestParam(defaultValue = "10") Long restaurantId
                                                                   ) {
        Page<ZoneDTO> zones = zoneService.getAllZonesByRestaurantId(restaurantId,page,size);
        return ResponseEntity.ok(zones);
    }
}