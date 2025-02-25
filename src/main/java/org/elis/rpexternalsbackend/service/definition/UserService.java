package org.elis.rpexternalsbackend.service.definition;

import org.elis.rpexternalsbackend.dto.request.CreateUserRequestDTO;
import org.elis.rpexternalsbackend.dto.request.LoginUserRequestDTO;
import org.elis.rpexternalsbackend.model.User;

import java.util.List;

public interface UserService {
    User signUp(CreateUserRequestDTO userRequestDTO);
    User login(LoginUserRequestDTO loginUserRequestDTO);
    Boolean isEmailPresent(String email);
    List<User> viewUsers();
}
