package org.inptdeveloppers.restaurantservice;

import org.inptdeveloppers.restaurantservice.entities.Restaurant;
import org.inptdeveloppers.restaurantservice.services.RestaurantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class RestaurantServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestaurantServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(RestaurantService restaurantService){
        return args -> {
            restaurantService.createRestaurant(Restaurant.builder().idOwner(1L).build());
        };
    }



}
