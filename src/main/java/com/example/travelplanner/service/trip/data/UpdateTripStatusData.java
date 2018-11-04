package com.example.travelplanner.service.trip.data;

import com.example.travelplanner.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class UpdateTripStatusData {

    private long tripId;
    private Status status;

}
