package org.inptdevelopers.reservationservice.clients;

import org.inptdevelopers.reservationservice.models.ATable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@FeignClient(name = "TABLE-SERVICE")
public interface TableClient {
    @GetMapping("/tables/{id}")
    @CircuitBreaker(name = "tableService", fallbackMethod = "getDefaultTable")
    ATable findTableById(@PathVariable  Long id);

   /*  @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getAllCustomers")
    List<Customer> allCustomers();
 */
    default ATable getDefaultTable(Long id, Exception exception) {
        ATable table = new ATable();
        table.setId(id);
        
        return table;
    }

    /* default List<Customer> getAllCustomers(Exception exception) {
        return List.of();
    } */
    @GetMapping("api/v1/tables/empty/{id}")
    List<ATable> findEmptytables( @PathVariable  Long id,@RequestHeader("Authorization") String accessToken);
    @PutMapping("api/v1/tables/{id}")
    ResponseEntity updateTable(@PathVariable Long id, @RequestBody ATable table);
}
