package com.example.travelplanner.util;

import com.example.travelplanner.domain.Trip;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.example.travelplanner.util.DateFormatter.getStringFromLocalDate;

public class TravelPlanBuilder {

    private String greetingPart;
    private String monthPart;
    private String tripsPart;
    private boolean username;
    private boolean month;
    private String travelPlan;

    public TravelPlanBuilder() {
        greetingPart = "Hello %s!";
        monthPart = "Here is your Travel plan for %s:";
        tripsPart = "";
        username = false;
        month = false;
        travelPlan = "";
    }

    public TravelPlanBuilder withUsername(String username) {
        if(this.username) return this;
        this.username = true;
        greetingPart = String.format(greetingPart, username);
        return this;
    }

    public TravelPlanBuilder withTrips(List<Trip> trips) {
        if(!trips.isEmpty()) {
            tripsPart += formatAllTrips(trips);
        }
        return this;
    }

    public TravelPlanBuilder withMonth(Month month) {
        if(this.month) return this;
        this.month = true;
        monthPart = String.format(monthPart, month.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        return this;
    }

    public String build() {
        if(username) {
          travelPlan += greetingPart + "\n\n";
        }
        if(month) {
            travelPlan += monthPart + "\n\n";
        }
        if(!tripsPart.equals("")) {
            travelPlan += tripsPart;
        } else {
            travelPlan += "There are no trips for selected month.";
        }
        return travelPlan;
    }

    private static String formatTrip(Trip trip) {
        String tripStr = "%s \n From %s to %s \n Comment: %s \n";
        return String.format(tripStr, trip.getDestination(), getStringFromLocalDate(trip.getStartDate()),
                                getStringFromLocalDate(trip.getEndDate()), trip.getComment());
    }

    private String formatAllTrips(List<Trip> trips) {
        return trips
                .stream()
                .map(TravelPlanBuilder::formatTrip)
                .collect(Collectors.joining("\n"));
    }
}
