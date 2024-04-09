package org.elis.rpexternalsbackend.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservation {
    private long id;
    private LocalDate date;
    private boolean takeAway;
}
