package com.example.travelplanner.scheduler;


import com.example.travelplanner.domain.Status;
import com.example.travelplanner.domain.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.example.travelplanner.util.DateUtils.*;

@Component
public class ScheduleTripStatusUpdate {

    @Autowired
    private TaskScheduler taskScheduler;

    private final Logger logger = LoggerFactory.getLogger(ScheduleTripStatusUpdate.class);

    public void scheduleTripStatusUpdate(Trip trip) {

        taskScheduler.schedule(new UpdateTripStatusTask(trip.getId(), Status.SOON), new CronTrigger(getCronExpressionFromLocalDate(trip.getStartDate().minusDays(1))));

        taskScheduler.schedule(new UpdateTripStatusTask(trip.getId(), Status.CURRENT), new CronTrigger(getCronExpressionFromLocalDate(trip.getStartDate())));

        taskScheduler.schedule(new UpdateTripStatusTask(trip.getId(), Status.PAST), new CronTrigger(getCronExpressionFromLocalDate(trip.getEndDate().plusDays(1))));

        logger.info("for SOON: " + getCronExpressionFromLocalDate(trip.getStartDate().minusDays(1)));
        logger.info("for CURRENT: " + getCronExpressionFromLocalDate(trip.getStartDate()));
        logger.info("for PAST: " + getCronExpressionFromLocalDate(trip.getEndDate().plusDays(1)));

    }

    private String getCronExpressionFromLocalDate(LocalDate date) {
        String cronExp = "0 0 0 %d %d ? %d";
        return String.format(cronExp, getDayIntFromLocalDate(date), getMonthIntFromLocalDate(date), getYearIntFromLocalDate(date));
    }



}
