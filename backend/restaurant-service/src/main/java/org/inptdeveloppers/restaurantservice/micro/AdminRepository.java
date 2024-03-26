package org.inptdeveloppers.restaurantservice.micro;

import org.inptdeveloppers.restaurantservice.models.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URL;

@FeignClient(name = "Restaurant-service",url="http://localhost:8082")
public interface AdminRepository {
    @GetMapping("/api/v1/admins/{id}")
    public Admin getAdmin(@RequestBody Long id);


}
