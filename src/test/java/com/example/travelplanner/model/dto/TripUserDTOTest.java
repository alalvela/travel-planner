package com.example.travelplanner.model.dto;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TripUserDTOTest {

    private Trip trip;
    private long userId = 2L;
    private String startDate = "14.12.2018";
    private String endDate = "19.12.2018";

    @Before
    public void setUp() throws Exception {
        trip = new Trip();
        trip.setUser(new User() {{ setId(userId); }});
        trip.setStartDate(LocalDate.of(2018, 12, 14));
        trip.setEndDate(LocalDate.of(2018, 12, 19));
    }

    @Test
    public void testConstructor() {

        TripUserDTO dto = new TripUserDTO(trip);

        assertEquals(dto.getUserId(), userId);
        assertEquals(dto.getStartDate(), startDate);
        assertEquals(dto.getEndDate(), endDate);
    }
}