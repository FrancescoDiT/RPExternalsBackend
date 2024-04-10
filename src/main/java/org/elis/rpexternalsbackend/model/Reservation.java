package org.elis.rpexternalsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private boolean takeAway;

    @ManyToOne
    private User user;
}
