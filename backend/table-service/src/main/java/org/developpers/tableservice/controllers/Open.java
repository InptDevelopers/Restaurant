package org.developpers.tableservice.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "USERS-SERVICE")
public interface Open {
    @GetMapping("/api/v1/waiters")
    Page<Waiter> waiters(@RequestHeader("Authorization") String accessToken);
}
