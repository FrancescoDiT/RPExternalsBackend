package org.elis.rpexternalsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @ManyToOne
    private User user;
}
