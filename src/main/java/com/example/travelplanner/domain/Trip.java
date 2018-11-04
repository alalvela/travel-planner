package com.example.travelplanner.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Trip(String destination, LocalDate startDate, LocalDate endDate, String comment) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comment = comment;
    }



}
