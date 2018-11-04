package com.example.travelplanner.service.impl;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.domain.User;
import com.example.travelplanner.model.dto.FutureTripDTO;
import com.example.travelplanner.model.dto.TripDTO;
import com.example.travelplanner.model.dto.TripUserDTO;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.service.TripService;
import com.example.travelplanner.util.TravelPlanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip addTrip(Trip trip) {
        tripRepository.insertTrip(trip.getDestination(), trip.getStartDate(), trip.getEndDate(), trip.getComment(), trip.getUser().getId());
        return trip;    //ne setuje id zbog native query za insert (void)
    }

    @Override
    public List<FutureTripDTO> getUserTrips(long userId) {
        List<Object[]> tripsAndDaysUntil = tripRepository.getUserTripsAndDaysUntil(userId);
        return tripsAndDaysUntil
                .stream()
                .map(FutureTripDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripDTO> searchUserTrips(long userId, String searchParam) {
        List<Trip> trips = tripRepository.searchUserTrips(userId, searchParam);
        return trips
                .stream()
                .map(TripDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripUserDTO> getAllTrips() {
        List<Trip> tripsAndDaysUntil = tripRepository.getAllTrips();
        return tripsAndDaysUntil
                .stream()
                .map(TripUserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TripUserDTO> searchAllTrips(String searchParam) {
        List<Trip> trips = tripRepository.searchAllTrips(searchParam);
        return trips
                .stream()
                .map(TripUserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getNextMonthsTravelPlan(User user) {
        LocalDate nextMonthDate = LocalDate.now().plusMonths(1);
        List<Trip> trips = tripRepository.getTripsFromMonthAndYear(user.getId(), nextMonthDate.getMonthValue(), nextMonthDate.getYear());
        TravelPlanBuilder planBuilder = new TravelPlanBuilder();
        return planBuilder
                .withUsername(user.getUsername())
                .withMonth(nextMonthDate.getMonth())
                .withTrips(trips)
                .build();
    }

}
