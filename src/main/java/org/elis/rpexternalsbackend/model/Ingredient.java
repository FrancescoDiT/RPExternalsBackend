package org.elis.rpexternalsbackend.model;

import lombok.Data;

@Data
public class Ingredient {
    private long id;
    private String name;
    private boolean frozen;
}
