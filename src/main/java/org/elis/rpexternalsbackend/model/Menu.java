package org.elis.rpexternalsbackend.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Menu {
    private long id;
    private LocalDate date;
}
