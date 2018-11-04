package com.example.travelplanner.scheduler;

import com.example.travelplanner.domain.Status;
import com.example.travelplanner.service.trip.UpdateTripStatus;
import com.example.travelplanner.service.trip.data.UpdateTripStatusData;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateTripStatusTask implements Runnable {

    @Autowired
    private UpdateTripStatus updateTripStatus;

    private long tripId;
    private Status status;

    public UpdateTripStatusTask(long tripId, Status status) {
        this.tripId = tripId;
        this.status = status;
    }

    @Override
    public void run() {
        updateTripStatus.execute(new UpdateTripStatusData(tripId, status));
    }
}
