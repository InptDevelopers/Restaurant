package org.inptdeveloppers.restaurantservice;

import org.inptdeveloppers.restaurantservice.entities.Item;
import org.inptdeveloppers.restaurantservice.repositories.ItemRepository;
import org.inptdeveloppers.restaurantservice.entities.Restaurant;
import org.inptdeveloppers.restaurantservice.services.RestaurantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients
public class RestaurantServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestaurantServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ItemRepository itemRepository) {
        return args -> {
            Item item1 = Item.builder()
                    .nom("poulet")
                    .price(70).build();
            Item item2 = Item.builder()
                    .nom("poulet")
                    .price(70).build();
            Item item3 = Item.builder()
                    .nom("poulet")
                    .price(70).build();
            Item item4 = Item.builder()
                    .nom("poulet")
                    .price(70).build();
            itemRepository.saveAll(Arrays.asList(item1, item2, item3, item4));
        };
    }

    @Bean
    CommandLineRunner start(RestaurantService restaurantService) {
        return args -> {
            restaurantService.createRestaurant(Restaurant.builder().idOwner(1L).build());
        };
    }

}
