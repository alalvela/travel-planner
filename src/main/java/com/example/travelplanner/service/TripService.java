package com.example.travelplanner.service;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.domain.User;
import com.example.travelplanner.model.dto.FutureTripDTO;
import com.example.travelplanner.model.dto.TripDTO;
import com.example.travelplanner.model.dto.TripUserDTO;

import java.util.List;

public interface TripService {

    public Trip addTrip(Trip trip);

    public List<FutureTripDTO> getUserTrips(long id);

    public List<TripDTO> searchUserTrips(long id, String searchParam);

    public List<TripUserDTO> getAllTrips();

    public List<TripUserDTO> searchAllTrips(String searchParam);

    public String getNextMonthsTravelPlan(User user);

}
