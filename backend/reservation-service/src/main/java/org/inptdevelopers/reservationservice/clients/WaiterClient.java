package org.inptdevelopers.reservationservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USERS-SERVICE")
public interface WaiterClient {

    @GetMapping("api/v1/waiters/available/{restaurantId}/reservations/{reservationId}")
    Long getWaiter(@PathVariable Long restaurantId, @PathVariable Long reservationId, @RequestHeader("Authorization") String accessToken);
    @GetMapping("api/v1/waiters/available/{restaurantId}")
    ResponseEntity<Long> getwait(@PathVariable Long restaurantId,  @RequestHeader("Authorization") String accessToken);
}
