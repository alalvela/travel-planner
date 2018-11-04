package com.example.travelplanner.repository;

import com.example.travelplanner.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO TRIP(destination, start_date, end_date, comment, user_id) " +
                    "VALUES(:destination, :startdate, :enddate, :comment, :userId)", nativeQuery = true)
    public int insertTrip(@Param("destination") String destination, @Param("startdate") LocalDate startDate,
                        @Param("enddate") LocalDate endDate, @Param("comment") String comment, @Param("userId") long userId);

    //podesiti sql dialect projekta na mysql da bi radilo datediff
    @Query(value = "SELECT id, destination, start_date, end_date, comment, datediff(start_date, current_date()) " +
                    "FROM TRIP " +
                    "WHERE user_id = :userId", nativeQuery = true)
    public List<Object[]> getUserTripsAndDaysUntil(@Param("userId") long userId);


    @Query(value = "SELECT * FROM TRIP " +
                    "WHERE comment <> '' " +
                    "AND (comment LIKE CONCAT('%',:searchParam,'%') OR destination LIKE CONCAT('%',:searchParam,'%')) " +
                    "AND user_id = :userId", nativeQuery = true)
    public List<Trip> searchUserTrips(@Param("userId") long userId, @Param("searchParam") String searchParam);


    @Query(value = "SELECT * FROM TRIP " +
                    "WHERE MONTH(start_date) = :month AND YEAR(start_date) = :year " +
                    "AND user_id = :userId " +
                    "ORDER BY start_date ASC", nativeQuery = true)
    public List<Trip> getTripsFromMonthAndYear(@Param("userId") long userId, @Param("month") int month, @Param("year") int year);


    //ADMIN
    @Query(value = "SELECT id, destination, start_date, end_date, comment, user_id " +
                    "FROM TRIP", nativeQuery = true)
    public List<Trip> getAllTrips();


    @Query(value = "SELECT * FROM TRIP " +
                    "WHERE comment <> '' " +
                    "AND (comment LIKE CONCAT('%',:searchParam,'%') OR destination LIKE CONCAT('%',:searchParam,'%'))", nativeQuery = true)
    public List<Trip> searchAllTrips(@Param("searchParam") String searchParam);
}
