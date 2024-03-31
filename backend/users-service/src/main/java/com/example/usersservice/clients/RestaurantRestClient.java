package com.example.usersservice.clients;

import com.example.usersservice.models.Restaurant;
import jakarta.ws.rs.POST;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "RESTAURANT-SERVICE")

public interface RestaurantRestClient {
    @PostMapping("/api/v1/restaurants")
    Restaurant createRestaurant(@RequestBody Restaurant restaurant, @RequestHeader String Authorization);
}
