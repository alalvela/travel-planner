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

    public List<Trip> findByUser_IdAndIsDeletedFalse(long id);

    public List<Trip> findByCommentContainingOrDestinationContainingAndUser_IdAndIsDeletedFalse(String comment, String destination, long id);

    public List<Trip> findByCommentContainingOrDestinationContainingAndIsDeletedFalse(String comment, String destination);

}
