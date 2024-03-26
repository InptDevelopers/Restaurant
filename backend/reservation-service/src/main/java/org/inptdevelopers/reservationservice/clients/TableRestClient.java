package org.inptdevelopers.reservationservice.clients;

import org.inptdevelopers.reservationservice.models.ATable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "TABLE-SERVICE")
public interface TableRestClient {
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

}
