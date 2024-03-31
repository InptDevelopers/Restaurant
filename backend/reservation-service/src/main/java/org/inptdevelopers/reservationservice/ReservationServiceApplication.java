package org.inptdevelopers.reservationservice;

import org.inptdevelopers.reservationservice.entities.Reservation;
import org.inptdevelopers.reservationservice.repositories.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ReservationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ReservationRepository reservationRepository) {
        return args -> {
            Reservation reservation1 = Reservation.builder()

                    .restaurantId(1L)
                    .clientId(1L)
                    .waiterId(2L)

                    .reservationTime(LocalDateTime.now())
                    .numGuests(4)
                    .build();

            Reservation reservation11 = Reservation.builder()

                    .restaurantId(1L)
                    .clientId(3L)
                    .waiterId(4L)

                    .reservationTime(LocalDateTime.now())
                    .numGuests(3)
                    .build();

            Reservation reservation2 = Reservation.builder()

                    .restaurantId(2L)
                    .clientId(2L)
                    .waiterId(3L)

                    .reservationTime(LocalDateTime.now())
                    .numGuests(3)
                    .build();

            Reservation reservation21 = Reservation.builder()

                    .restaurantId(2L)
                    .clientId(3L)
                    .waiterId(3L)

                    .reservationTime(LocalDateTime.now())
                    .numGuests(2)
                    .build();

            reservationRepository.saveAll(Arrays.asList(reservation1, reservation2, reservation11, reservation21));
        };
    }

}
