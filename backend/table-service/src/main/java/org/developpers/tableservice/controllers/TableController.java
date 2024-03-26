package org.developpers.tableservice.controllers;

import lombok.AllArgsConstructor;
import org.developpers.tableservice.dtos.TableDTO;
import org.developpers.tableservice.exceptions.TableNotFoundException;
import org.developpers.tableservice.services.TableService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/tables")
@CrossOrigin("*")

@AllArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping("/{id}")
    public ResponseEntity getTableById(@PathVariable Long id) {
        try {
            TableDTO tableDTO = tableService.getTableById(id);
            return ResponseEntity.ok(tableDTO);
        } catch (TableNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTable(@PathVariable Long id) {
        try {
            tableService.deleteTable(id);
            return ResponseEntity.noContent().build();
        } catch (TableNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTable(@PathVariable Long id, @RequestBody TableDTO tableDTO) {
        try {
            TableDTO updatedTable = tableService.updateTable(id, tableDTO);
            return ResponseEntity.ok(updatedTable);
        } catch (TableNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Page<TableDTO>> getAllTablesByRestaurantId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
            ,  @RequestParam Long restaurantId
    ) {
        Page<TableDTO> tables = tableService.getAllTablesByRestaurantId(restaurantId,page,size);
        return ResponseEntity.ok(tables);
    }
}