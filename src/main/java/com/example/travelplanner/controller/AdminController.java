package com.example.travelplanner.controller;

import com.example.travelplanner.model.dto.TripUserDTO;
import com.example.travelplanner.service.trip.GetTrips;
import com.example.travelplanner.service.trip.SearchTrips;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Api(value = "admin")
public class AdminController {

    private GetTrips getTrips;
    private SearchTrips searchTrips;

    @Autowired
    public AdminController(GetTrips getTrips, SearchTrips searchTrips) {
        this.getTrips = getTrips;
        this.searchTrips = searchTrips;
    }

    @GetMapping("/trip")
    @ApiOperation(value = "Get all user trips.", notes = "Retrieves all user trips. Authenticated user must have ADMIN authority.", httpMethod = "GET", produces = "application/dto")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Trips successfully retrieved") })
    public ResponseEntity<List<TripUserDTO>> getAllTrips() {
        List<TripUserDTO> trips = getTrips.byAll();
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/trip/{searchParam}")
    @ApiOperation(value = "Search all user trips.", notes = "Searches user trips by comment or destination. Authenticated user must have ADMIN authority.", httpMethod = "GET", produces = "application/dto")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Trips successfully retrieved") })
    public ResponseEntity<List<TripUserDTO>> searchAllTrips(@ApiParam(value = "Search key") @PathVariable("searchParam") String searchParam) {
        List<TripUserDTO> trips = searchTrips.byCommentOrDescription(searchParam);
        return ResponseEntity.ok(trips);
    }
}

