package org.elis.rpexternalsbackend.model;

import lombok.Data;

@Data
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
