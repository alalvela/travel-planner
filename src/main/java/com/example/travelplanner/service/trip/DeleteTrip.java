package com.example.travelplanner.service.trip;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.service.trip.data.DeleteTripData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@Service
@Transactional
public class DeleteTrip {

    private TripRepository tripRepository;

    @Autowired
    public DeleteTrip(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public void execute(DeleteTripData data) {
        Trip trip = tripRepository.findById(data.getTripId()).orElseThrow(NotFoundException::new);
        trip.setDeleted(true);
        tripRepository.save(trip);
    }

}
