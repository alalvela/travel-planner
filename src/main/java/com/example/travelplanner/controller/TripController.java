package com.example.travelplanner.controller;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.domain.User;
import com.example.travelplanner.exception.InvalidDatesException;
import com.example.travelplanner.model.dto.TripDTO;
import com.example.travelplanner.model.dto.FutureTripDTO;
import com.example.travelplanner.service.TripService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trip")
@Api(value = "trips")
public class TripController {

    private TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    @PostMapping
    @ApiOperation(value = "Create new trip.", notes = "Returns trip created. User must be authenticated.", httpMethod = "POST", code = 201, produces = "application/dto", consumes = "application/dto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Trip successfully created"),
            @ApiResponse(code = 400, message = "Invalid trip dates")
    })
    public ResponseEntity<TripDTO> createTrip(@ApiParam(value = "Trip tripDTO object", required = true) @RequestBody TripDTO tripDTO) {

        Trip trip = tripDTO.extractTripFromTripDTO();

        User authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        trip.setUser(authUser);

        if(!trip.getStartDate().isBefore(trip.getEndDate())) {
            throw new InvalidDatesException("Start date must come before end date!");
        }

        LocalDate today = LocalDate.now();
        if(trip.getStartDate().isBefore(today)) {
            throw new InvalidDatesException("Start date must be today or after!");
        }

        tripService.addTrip(trip);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TripDTO(trip));
    }


    @GetMapping
    @ApiOperation(value = "Get trips.", notes = "Retrieves all trips of authenticated user with day count until trip start. If trip passed, day count is set to -1.", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Trips successfully retrieved") })
    public ResponseEntity<List<FutureTripDTO>> getTrips() {
        long userId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<FutureTripDTO> trips =  tripService.getUserTrips(userId);
        return ResponseEntity.ok(trips);
    }


    @GetMapping("/{searchParam}")
    @ApiOperation(value = "Search trips.", notes = "Searches authenticated user trips by comment or description.", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Trips successfully retrieved") })
    public ResponseEntity<List<TripDTO>> searchTrips(@ApiParam(value = "Search key") @PathVariable("searchParam") String searchParam) {
        long userId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<TripDTO> trips = tripService.searchUserTrips(userId, searchParam);
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/plan")
    @Produces("text/plain")
    @ApiOperation(value = "Get travel plan for next month.", notes = "Returns travel plan for next month in plain text format.", httpMethod = "GET", produces = "text/plain")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Travel plan successfully retrieved") })
    public String getNextMonthsTravelPlan() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return tripService.getNextMonthsTravelPlan(user);
    }

}
