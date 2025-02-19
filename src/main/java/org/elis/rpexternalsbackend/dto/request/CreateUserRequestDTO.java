package org.elis.rpexternalsbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String repeatPassword;
}
