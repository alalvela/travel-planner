//package com.example.travelplanner.service.impl;
//
//import com.example.travelplanner.domain.Trip;
//import com.example.travelplanner.domain.User;
//import com.example.travelplanner.model.dto.TripDTO;
//import com.example.travelplanner.model.dto.TripUserDTO;
//import com.example.travelplanner.repository.TripRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//public class TripServiceTest {
//
//    TripService tripService;
//
//    @Mock
//    TripRepository tripRepository;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//
//        tripService = new TripService(tripRepository);
//    }
//
////    @Test
////    public void getUserTrips() {
////        Object[] tripsAndDaysObject1 = {
////                BigInteger.valueOf(1L),
////                "Destination1",
////                Date.valueOf(LocalDate.of(2018, 11, 10)),
////                Date.valueOf(LocalDate.of(2018, 11, 14)),
////                "Comment1",
////                BigInteger.valueOf(345)
////        };
////        Object[] tripsAndDaysObject2 = {
////                BigInteger.valueOf(2L),
////                "Destination2",
////                Date.valueOf(LocalDate.of(2018, 11, 10)),
////                Date.valueOf(LocalDate.of(2018, 11, 14)),
////                "Comment2",
////                BigInteger.valueOf(3455)
////        };
////
////        List<Object[]> objs = new ArrayList<>();
////        objs.add(tripsAndDaysObject1);
////        objs.add(tripsAndDaysObject2);
////
////        long userId = 1L;
////
////        when(tripRepository.getUserTripsAndDaysUntil(userId)).thenReturn(objs);
////
////        assertEquals(tripRepository.getUserTripsAndDaysUntil(userId).size(), 2);
////
////        List<FutureTripDTO> trips = tripService.getUserTrips(userId);
////
////        assertEquals(trips.size(), 2);
////
////        FutureTripDTO firstTripDto = trips.get(0);
////
////        assertEquals(trips.size(), 2);
////        assertNotNull(firstTripDto);
////        assertEquals(firstTripDto.getId(), 1L);
////        assertEquals(firstTripDto.getStartDate(), "10.11.2018");
////        assertEquals(firstTripDto.getEndDate(), "14.11.2018");
////        assertEquals(firstTripDto.getDaysUntil(), 345);
////
////    }
//
//    @Test
//    public void searchUserTrips() {
//
//        long user1Id = 1L;
//        User user1 = new User() {{ setId(user1Id); }};
//        Trip trip1 = new Trip(1L, "Dest1", LocalDate.of(2018, 11, 10), LocalDate.of(2018, 11, 10), "Comm1", user1);
//        Trip trip2 = new Trip(2L, "Dest2", LocalDate.of(2018, 11, 10), LocalDate.of(2018, 11, 10), "Comm2", user1);
//
//        List<Trip> trips = new ArrayList<>();
//        trips.add(trip1);
//        trips.add(trip2);
//
//        when(tripRepository.findByCommentContainingOrDestinationContainingAndUser_IdAndIsDeletedFalse("dest", "dest", user1Id )).thenReturn(trips);
//
//        assertEquals(tripRepository.findByCommentContainingOrDestinationContainingAndUser_IdAndIsDeletedFalse("dest", "dest", user1Id).size(), 2);
//
//        List<TripDTO> tripDtos = tripService.searchUserTrips(user1Id, "dest");
//
//        assertEquals(tripDtos.size(), 2);
//
//
//        TripDTO tripDto = tripDtos.get(0);
//
//        assertEquals(tripDto.getId(), user1Id);
//        assertEquals(tripDto.getStartDate(), "10.11.2018");
//        assertEquals(tripDto.getEndDate(), "10.11.2018");
//        assertEquals(tripDto.getDestination(), "Dest1");
//
//    }
//
//    @Test
//    public void getAllTrips() {
//
//        long user1Id = 1L;
//        User user1 = new User() {{ setId(user1Id); }};
//        Trip trip1 = new Trip(1L, "Dest1", LocalDate.of(2018, 11, 10), LocalDate.of(2018, 11, 10), "Comm1", user1);
//        Trip trip2 = new Trip(2L, "Dest2", LocalDate.of(2018, 11, 10), LocalDate.of(2018, 11, 10), "Comm2", user1);
//
//        List<Trip> trips = new ArrayList<>();
//        trips.add(trip1);
//        trips.add(trip2);
//
//        when(tripRepository.findAll()).thenReturn(trips);
//
//        assertEquals(tripRepository.findAll().size(), 2);
//
//        List<TripUserDTO> tripDtos = tripService.getAllTrips();
//
//        assertEquals(tripDtos.size(), 2);
//
//        TripUserDTO first = tripDtos.get(0);
//
//        assertEquals(first.getId(), 1L);
//        assertEquals(first.getStartDate(), "10.11.2018");
//        assertEquals(first.getEndDate(), "10.11.2018");
//        assertEquals(first.getUserId(), user1Id);
//
//    }
//
//
//    @Test
//    public void searchAllTrips() {
//        long user1Id = 1L;
//        User user1 = new User() {{ setId(user1Id); }};
//        Trip trip1 = new Trip(1L, "Dest1", LocalDate.of(2018, 11, 10), LocalDate.of(2018, 11, 10), "Comm1", user1);
//        Trip trip2 = new Trip(2L, "Dest2", LocalDate.of(2018, 11, 10), LocalDate.of(2018, 11, 10), "Comm2", user1);
//
//        List<Trip> trips = new ArrayList<>();
//        trips.add(trip1);
//        trips.add(trip2);
//
//        when(tripRepository.findByCommentContainingOrDestinationContainingAndIsDeletedFalse("param", "param")).thenReturn(trips);
//
//        assertEquals(tripRepository.findByCommentContainingOrDestinationContainingAndIsDeletedFalse("param", "param").size(), 2);
//
//        List<TripUserDTO> tripDtos = tripService.searchAllTrips("param");
//
//        assertEquals(tripDtos.size(), 2);
//
//        TripUserDTO first = tripDtos.get(0);
//
//        assertEquals(first.getId(), 1L);
//        assertEquals(first.getStartDate(), "10.11.2018");
//        assertEquals(first.getEndDate(), "10.11.2018");
//        assertEquals(first.getUserId(), user1Id);
//    }
//}