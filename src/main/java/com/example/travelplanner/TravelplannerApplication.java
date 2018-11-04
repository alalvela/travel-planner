package com.example.travelplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class TravelplannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelplannerApplication.class, args);
    }
}
