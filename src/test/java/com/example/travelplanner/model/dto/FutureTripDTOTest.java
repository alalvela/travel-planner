package com.example.travelplanner.model.dto;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class FutureTripDTOTest {

    private Object[] objArray;
    private long id = 1L;
    private String destination = "Destination";
    private String comment = "Comment";
    private String startDate = "10.11.2018";
    private String endDate = "14.11.2018";
    private int daysUntil = 234;

    @Before
    public void setUp() {
        objArray = new Object[6];
        objArray[0] = BigInteger.valueOf(id);
        objArray[1] = destination;
        objArray[2] = Date.valueOf(LocalDate.of(2018, 11, 10)); //sql.Date
        objArray[3] = Date.valueOf(LocalDate.of(2018, 11, 14));
        objArray[4] = comment;
        objArray[5] = BigInteger.valueOf(daysUntil);
    }

    @Test
    public void testConstructor() {

        FutureTripDTO dto = new FutureTripDTO(objArray);

        assertEquals(dto.getId(), id);
        assertEquals(dto.getDestination(), destination);
        assertEquals(dto.getComment(), comment);
        assertEquals(dto.getStartDate(), startDate);
        assertEquals(dto.getEndDate(), endDate);
        assertEquals(dto.getDaysUntil(), daysUntil);

        objArray[5] = BigInteger.valueOf(-345);
        FutureTripDTO dto1 = new FutureTripDTO(objArray);

        assertEquals(dto1.getDaysUntil(), -1);

    }

}