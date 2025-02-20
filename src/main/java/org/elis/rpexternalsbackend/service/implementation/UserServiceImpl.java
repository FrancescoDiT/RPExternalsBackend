package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateUserRequestDTO;
import org.elis.rpexternalsbackend.dto.request.LoginUserRequestDTO;
import org.elis.rpexternalsbackend.dto.response.ViewUsersDTO;
import org.elis.rpexternalsbackend.exception.LogInFailureException;
import org.elis.rpexternalsbackend.exception.SignInFailureException;
import org.elis.rpexternalsbackend.model.User;
import org.elis.rpexternalsbackend.model.UserType;
import org.elis.rpexternalsbackend.repository.UserRepository;
import org.elis.rpexternalsbackend.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User signUp(CreateUserRequestDTO userRequestDTO) {

        Map<String, String> errors = new TreeMap<>();

        //check for email already exists in db
        assert userRequestDTO.getEmail() != null;
        if (isEmailPresent(userRequestDTO.getEmail())) {
            errors.put("checkEmailEquals", "account with this email already exists");
        }

        //check for passwords to be equals
        assert userRequestDTO.getPassword() != null;
        if (!userRequestDTO.getPassword().equals(userRequestDTO.getRepeatPassword())){
            errors.put("checkPassword", "passwords do not match");
        }

        if(!errors.isEmpty()){
            throw new SignInFailureException(errors);
        }

        User user = new User();
        user.setEmail(userRequestDTO.getEmail().trim());
        user.setPassword(userRequestDTO.getPassword().trim());
        user.setName(userRequestDTO.getName().trim());
        user.setSurname(userRequestDTO.getSurname().trim());
        user.setUserType(UserType.EXTERNAL);
        return userRepository.save(user);
    }

    public boolean isEmailPresent(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;  // Se un utente con quella email esiste, ritorna true
    }

    @Override
    public User login(LoginUserRequestDTO loginUserRequestDTO) {

        Map<String, String> errors = new TreeMap<>();

        //check for email already exists in db
        assert loginUserRequestDTO.getEmail() != null;
        if (!loginUserRequestDTO.getEmail().equals(userRepository.findByEmail(loginUserRequestDTO.getEmail()).getEmail())) {
            errors.put("checkEmailEquals", "account doesn't exists");
        }

        assert loginUserRequestDTO.getPassword() != null;
        if(!loginUserRequestDTO.getPassword().equals(userRepository.findByEmail(loginUserRequestDTO.getEmail()).getPassword())){
            errors.put("checkPassword", "wrong credentials");
        }

        if(!errors.isEmpty()){
            throw new LogInFailureException(errors);
        }

        return null;
    }

    @Override
    public List<ViewUsersDTO> viewUsers() {
        return userRepository.findAll().stream()
                .map(user -> new ViewUsersDTO(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getUserType())) // Converte User in ViewUsersDTO
                .toList(); // Raccoglie la lista risultante
    }
}
