package com.example.travelplanner.service.trip;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.domain.User;
import com.example.travelplanner.repository.TripRepository;
import com.example.travelplanner.util.TravelPlanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetTravelPlan {

    private TripRepository tripRepository;

    @Autowired
    public GetTravelPlan(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public String forNextMonth(User user) {
        List<Trip> userTrips = tripRepository.findByUser_IdAndIsDeletedFalse(user.getId());
        LocalDate nextMonthDate = LocalDate.now().plusMonths(1);

        int nextMonth = nextMonthDate.getMonthValue();
        int nextYear = nextMonthDate.getYear();

        List<Trip> nextMonthTrips = getNextMonthTrips(userTrips, nextMonth, nextYear);

        TravelPlanBuilder planBuilder = new TravelPlanBuilder();
        return planBuilder
                .withUsername(user.getUsername())
                .withMonth(nextMonthDate.getMonth())
                .withTrips(nextMonthTrips)
                .build();
    }

    private List<Trip> getNextMonthTrips(List<Trip> userTrips, int nextMonth, int nextYear) {
        return userTrips
                    .stream()
                    .filter(t -> (t.getStartDate().getMonthValue()) == nextMonth && t.getStartDate().getYear() == nextYear)
                    .collect(Collectors.toList());
    }
}
