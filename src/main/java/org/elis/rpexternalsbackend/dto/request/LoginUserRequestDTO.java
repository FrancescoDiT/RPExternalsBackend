package org.elis.rpexternalsbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserRequestDTO {
    private String email;
    private String password;
}
