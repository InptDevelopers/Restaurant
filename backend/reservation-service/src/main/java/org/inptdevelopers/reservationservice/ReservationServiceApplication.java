package org.inptdevelopers.reservationservice;

import org.inptdevelopers.reservationservice.entities.Reservation;
import org.inptdevelopers.reservationservice.repositories.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class ReservationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ReservationRepository reservationRepository) {
        return args -> {
            Reservation reservation1 = Reservation.builder()
                    .tableIds(Arrays.asList(1L, 2L))
                    .restaurantId(1L)
                    .clientId(1L)
                    .waiterId(2L)
                    .reservationTime(LocalDateTime.now())
                    .numGuests(4)
                    .build();

            Reservation reservation2 = Reservation.builder()
                    .tableIds(Arrays.asList(3L, 4L))
                    .restaurantId(2L)
                    .clientId(2L)
                    .waiterId(3L)
                    .reservationTime(LocalDateTime.now())
                    .numGuests(3)
                    .build();

            reservationRepository.saveAll(Arrays.asList(reservation1, reservation2));
        };
    }

}
