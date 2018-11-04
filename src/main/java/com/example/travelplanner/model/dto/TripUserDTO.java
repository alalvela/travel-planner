package com.example.travelplanner.model.dto;

import com.example.travelplanner.domain.Trip;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.travelplanner.util.DateUtils.getStringFromLocalDate;

@Getter @Setter @NoArgsConstructor
public class TripUserDTO {

    private long id;
    private String destination;
    private String startDate;
    private String endDate;
    private String comment;
    private String status;
    private long userId;

    public TripUserDTO(Trip trip) {
        this.id = trip.getId();
        this.destination = trip.getDestination();
        this.startDate = getStringFromLocalDate(trip.getStartDate());
        this.endDate = getStringFromLocalDate(trip.getEndDate());
        this.comment = trip.getComment();
        this.status = trip.getStatus().toString();
        this.userId = trip.getUser().getId();
    }

}
