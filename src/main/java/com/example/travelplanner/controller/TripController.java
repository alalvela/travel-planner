package com.example.travelplanner.controller;

import com.example.travelplanner.scheduler.ScheduleTripStatusUpdate;
import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.domain.User;
import com.example.travelplanner.exception.InvalidDatesException;
import com.example.travelplanner.model.dto.TripDTO;
import com.example.travelplanner.model.dto.FutureTripDTO;
import com.example.travelplanner.service.trip.*;
import com.example.travelplanner.service.trip.data.DeleteTripData;
import com.example.travelplanner.service.user.GetUser;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    private CreateTrip createTrip;
    private GetTrips getTrips;
    private SearchTrips searchTrips;
    private GetTravelPlan getTravelPlan;
    private DeleteTrip deleteTrip;
    private ScheduleTripStatusUpdate scheduleTripStatusUpdate;
    private GetUser getUser;

    @Autowired
    public TripController(CreateTrip createTrip, GetTrips getTrips, SearchTrips searchTrips, GetTravelPlan getTravelPlan, DeleteTrip deleteTrip, ScheduleTripStatusUpdate scheduleTripStatusUpdate, GetUser getUser) {
        this.createTrip = createTrip;
        this.getTrips = getTrips;
        this.searchTrips = searchTrips;
        this.getTravelPlan = getTravelPlan;
        this.deleteTrip = deleteTrip;
        this.scheduleTripStatusUpdate = scheduleTripStatusUpdate;
        this.getUser = getUser;
    }

    @PostMapping
    @ApiOperation(value = "Create new trip.", notes = "Returns trip created. User must be authenticated.", httpMethod = "POST", code = 201, produces = "application/dto", consumes = "application/dto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Trip successfully created"),
            @ApiResponse(code = 400, message = "Invalid trip dates")
    })
    public ResponseEntity<TripDTO> createTrip(@ApiParam(value = "Trip tripDTO object", required = true) @RequestBody TripDTO tripDTO) {

        Trip trip = tripDTO.extractTripFromTripDTO();

        User authUser = getUser.byId((long)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        trip.setUser(authUser);

        if(!trip.getStartDate().isBefore(trip.getEndDate())) {
            throw new InvalidDatesException("Start date must come before end date!");
        }

        LocalDate today = LocalDate.now();
        if(trip.getStartDate().isBefore(today)) {
            throw new InvalidDatesException("Start date must be after today!");
        }

        createTrip.execute(trip);
        scheduleTripStatusUpdate.scheduleTripStatusUpdate(trip);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TripDTO(trip));
    }


    @GetMapping
    @ApiOperation(value = "Get trips.", notes = "Retrieves all trips of authenticated user with day count until trip start. If trip passed, day count is set to -1.", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Trips successfully retrieved") })
    public ResponseEntity<List<FutureTripDTO>> getTrips() {
        String stagod = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        logger.info("Authentication principal: " + stagod);

        long userId = (long)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<FutureTripDTO> trips =  getTrips.byUser(userId);
        return ResponseEntity.ok(trips);
    }


    @GetMapping("/{searchParam}")
    @ApiOperation(value = "Search trips.", notes = "Searches authenticated user trips by comment or description.", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Trips successfully retrieved") })
    public ResponseEntity<List<TripDTO>> searchTrips(@ApiParam(value = "Search key") @PathVariable("searchParam") String searchParam) {
        long userId = (long)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TripDTO> trips = searchTrips.byUserAndCommentOrDescription(userId, searchParam);
        return ResponseEntity.ok(trips);
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity deleteTrip(@PathVariable long tripId) {
        deleteTrip.execute(new DeleteTripData(tripId));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/plan")
    @Produces("text/plain")
    @ApiOperation(value = "Get travel plan for next month.", notes = "Returns travel plan for next month in plain text format.", httpMethod = "GET", produces = "text/plain")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Travel plan successfully retrieved") })
    public String getNextMonthsTravelPlan() {
        User user = getUser.byId((long)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return getTravelPlan.forNextMonth(user);
    }

}
