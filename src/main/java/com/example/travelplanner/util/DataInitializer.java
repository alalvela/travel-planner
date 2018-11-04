package com.example.travelplanner.util;

import com.example.travelplanner.domain.Status;
import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.domain.User;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements ApplicationRunner {

    private UserRepository userRepository;
    private TripRepository tripRepository;

    @Autowired
    public DataInitializer(UserRepository userRepository, TripRepository tripRepository) {
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User u1 = new User(1L, "user", "$2a$10$SjpVAO.vRWA2KJdfg/LKiOYBlGaFaoBJAzTQ60sb5FPOCpbu9BtKa", "USER");
        User u2 = new User(2L, "admin", "$2a$10$SjpVAO.vRWA2KJdfg/LKiOYBlGaFaoBJAzTQ60sb5FPOCpbu9BtKa", "ADMIN");

        userRepository.save(u1);
        userRepository.save(u2);

        Trip t1 = new Trip(
                1L,
                "Rome",
                LocalDate.of(2018, 11, 14),
                LocalDate.of(2018, 11, 20),
                "Najbolji do sad",
                Status.PAST,
                false,
                u1);

        Trip t2 = new Trip(
                2L,
                "Havana",
                LocalDate.of(2018, 12, 14),
                LocalDate.of(2018, 12, 20),
                "Vrh",
                Status.UPCOMING,
                false,
                u1);

        Trip t3 = new Trip(
                3L,
                "Ancona",
                LocalDate.of(2018, 11, 14),
                LocalDate.of(2018, 11, 20),
                "Super",
                Status.PAST,
                false,
                u2);

        Trip t4 = new Trip(
                4L,
                "Matera",
                LocalDate.of(2019, 11, 14),
                LocalDate.of(2019, 11, 20),
                "Super",
                Status.UPCOMING,
                false,
                u2);

        Trip t5 = new Trip(
                5L,
                "London",
                LocalDate.of(2019, 01, 14),
                LocalDate.of(2019, 01, 20),
                "Super",
                Status.UPCOMING,
                false,
                u2);

        tripRepository.save(t1);
        tripRepository.save(t2);
        tripRepository.save(t3);
        tripRepository.save(t4);
        tripRepository.save(t5);


    }
}
