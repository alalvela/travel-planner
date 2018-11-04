package com.example.travelplanner.service.trip;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.model.dto.TripDTO;
import com.example.travelplanner.model.dto.TripUserDTO;
import com.example.travelplanner.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SearchTrips {

    private TripRepository tripRepository;

    @Autowired
    public SearchTrips(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<TripDTO> byUserAndCommentOrDescription(long userId, String searchParam) {
        List<Trip> trips = tripRepository.findByCommentContainingOrDestinationContainingAndUser_IdAndIsDeletedFalse(searchParam, searchParam, userId);
        return trips
                .stream()
                .map(TripDTO::new)
                .collect(Collectors.toList());
    }

    public List<TripUserDTO> byCommentOrDescription(String searchParam) {
        List<Trip> trips = tripRepository.findByCommentContainingOrDestinationContainingAndIsDeletedFalse(searchParam, searchParam);
        return trips
                .stream()
                .map(TripUserDTO::new)
                .collect(Collectors.toList());
    }

}
