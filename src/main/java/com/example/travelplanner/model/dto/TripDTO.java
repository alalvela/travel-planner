package com.example.travelplanner.model.dto;

import com.example.travelplanner.domain.Trip;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static com.example.travelplanner.util.DateUtils.getLocalDateFromString;
import static com.example.travelplanner.util.DateUtils.getStringFromLocalDate;

@Getter @Setter @NoArgsConstructor
public class TripDTO {

    private long id;
    private String destination;
    private String startDate;
    private String endDate;
    private String comment;
    private String status;

    public TripDTO(Trip trip) {
        this.id = trip.getId();
        this.destination = trip.getDestination();
        this.startDate = getStringFromLocalDate(trip.getStartDate());
        this.endDate = getStringFromLocalDate(trip.getEndDate());
        this.comment = trip.getComment();
        this.status = trip.getStatus().toString();
    }

    public Trip extractTripFromTripDTO() {
        LocalDate startDateL = getLocalDateFromString(this.startDate);
        LocalDate endDateL = getLocalDateFromString(this.endDate);
        return new Trip(this.destination, startDateL, endDateL, this.comment);
    }

}
