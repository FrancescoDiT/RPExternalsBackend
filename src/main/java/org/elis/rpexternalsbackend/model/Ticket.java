package org.elis.rpexternalsbackend.model;

import lombok.Data;

@Data
public class Ticket {
    private long id;
    private String title;
    private String body;
}
