package com.example.travelplanner.service.trip;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.service.trip.data.UpdateTripStatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

@Service
public class UpdateTripStatus {

    private TripRepository tripRepository;

    @Autowired
    public UpdateTripStatus(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public void execute(UpdateTripStatusData data) {
        Trip trip = tripRepository.findById(data.getTripId()).orElseThrow(NotFoundException::new);
        if (trip.isDeleted()) return;
        trip.setStatus(data.getStatus());
        tripRepository.save(trip);
    }

}
