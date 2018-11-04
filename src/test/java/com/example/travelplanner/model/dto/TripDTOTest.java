package com.example.travelplanner.model.dto;

import com.example.travelplanner.domain.Trip;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TripDTOTest {

    private Trip trip;

    @Before
    public void setUp() throws Exception {
        trip = new Trip();
        trip.setDestination("Destination");
        trip.setComment("Comment");
        trip.setStartDate(LocalDate.of(2018, 12, 14));
        trip.setEndDate(LocalDate.of(2018, 12, 19));
    }


    @Test
    public void extractTripFromTripDTO() {

        TripDTO dto = new TripDTO(trip);
        Trip extracted = dto.extractTripFromTripDTO();

        assertEquals(trip.getComment(), extracted.getComment());
        assertEquals(trip.getDestination(), extracted.getDestination());
        assertEquals(trip.getStartDate(), extracted.getStartDate());
        assertEquals(trip.getEndDate(), extracted.getEndDate());

    }
}