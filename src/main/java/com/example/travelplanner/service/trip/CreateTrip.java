package com.example.travelplanner.service.trip;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateTrip {

    private TripRepository tripRepository;

    @Autowired
    public CreateTrip(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip execute(Trip trip) {
        return tripRepository.save(trip);
    }

}
