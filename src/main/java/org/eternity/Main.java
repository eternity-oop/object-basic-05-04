package org.eternity;

import org.eternity.reservation.domain.Reservation;
import org.eternity.reservation.service.ReservationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    @Bean
    CommandLineRunner commandLineRunner(ReservationService reservationService) {
        return args -> {
            Reservation reservation = reservationService.reserveScreening(1L, 1L, 2);
            System.out.println("영화 요금 : " + reservation.getFee());
        };
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
    }
}
