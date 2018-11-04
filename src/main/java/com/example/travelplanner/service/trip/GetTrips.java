package com.example.travelplanner.service.trip;

import com.example.travelplanner.domain.Trip;
import com.example.travelplanner.model.dto.FutureTripDTO;
import com.example.travelplanner.model.dto.TripUserDTO;
import com.example.travelplanner.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.travelplanner.util.DateUtils.getLocalDateFromString;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional
public class GetTrips {

    @PersistenceContext
    private EntityManager entityManager;

    private TripRepository tripRepository;


    @Autowired
    public GetTrips(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<FutureTripDTO> byUser(long userId) {
        List<FutureTripDTO> dtoList = new ArrayList<>();

        tripRepository.findByUser_IdAndIsDeletedFalse(userId)
                .stream()
//                .filter(t -> t.getStartDate().isAfter(LocalDate.now()))
                .map(FutureTripDTO::new)
                .forEach(dto -> {
                    dto.setDaysUntil((int)DAYS.between(LocalDate.now(), getLocalDateFromString(dto.getStartDate())));
                    dtoList.add(dto);
                });

        return dtoList;
    }

    public List<TripUserDTO> byAll() {
        List<Trip> tripsAndDaysUntil = tripRepository.findAll();
        return tripsAndDaysUntil
                .stream()
                .map(TripUserDTO::new)
                .collect(Collectors.toList());
    }

}
