package com.example.travelplanner.model.dto;

import com.example.travelplanner.domain.Trip;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Date;

import static com.example.travelplanner.util.DateUtils.getStringFromLocalDate;

@Getter @Setter @NoArgsConstructor
public class FutureTripDTO {

    private long id;
    private String destination;
    private String startDate;
    private String endDate;
    private String comment;
    private String status;
    private int daysUntil;

    public FutureTripDTO(Trip trip) {
        this.id = trip.getId();
        this.destination = trip.getDestination();
        this.startDate = getStringFromLocalDate(trip.getStartDate());
        this.endDate = getStringFromLocalDate(trip.getEndDate());
        this.comment = trip.getComment();
        this.status = trip.getStatus().toString();
    }

    public FutureTripDTO(Object[] resultSet) {
        this.id = ((BigInteger)resultSet[0]).longValue();
        this.destination = (String)resultSet[1];
        this.startDate = getStringFromLocalDate( ((Date)resultSet[2]).toLocalDate() );
        this.endDate = getStringFromLocalDate( ((Date)resultSet[3]).toLocalDate() );
        this.comment = (String)resultSet[4];
        int daysUntil = ((BigInteger)resultSet[5]).intValue();
        this.daysUntil = daysUntil >= 0 ? daysUntil : -1;
    }

}
