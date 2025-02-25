package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateUserRequestDTO;
import org.elis.rpexternalsbackend.dto.request.LoginUserRequestDTO;
import org.elis.rpexternalsbackend.exception.LogInFailureException;
import org.elis.rpexternalsbackend.exception.SignUpFailureException;
import org.elis.rpexternalsbackend.model.User;
import org.elis.rpexternalsbackend.model.value.UserType;
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
    public User signUp(CreateUserRequestDTO createUserRequestDTO) {

        Map<String, String> errors = new TreeMap<>();

        //Check for email already exists in db
        if (isEmailPresent(createUserRequestDTO.getEmail())) {
            errors.put("EmailAlreadyOnDb", "Account with this email already exists");
        }

        //Check for passwords to be equals
        if (!createUserRequestDTO.getPassword().equals(createUserRequestDTO.getRepeatPassword())){
            errors.put("CheckPassword", "Passwords do not match");
        }

        if(!errors.isEmpty()){
            throw new SignUpFailureException(errors);
        }

        User user = User.builder()
                        .name(createUserRequestDTO.getName().trim())
                        .surname(createUserRequestDTO.getSurname().trim())
                        .email(createUserRequestDTO.getEmail().trim())
                        .password(createUserRequestDTO.getPassword().trim())
                        .userType(UserType.EXTERNAL)
                        .build();
        return userRepository.save(user);
    }

    @Override
    public Boolean isEmailPresent(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public User login(LoginUserRequestDTO loginUserRequestDTO) {

        Map<String, String> errors = new TreeMap<>();

        User savedUser = userRepository.findByEmail(loginUserRequestDTO.getEmail());

        //Check for email already exists in db
        if (!loginUserRequestDTO.getEmail().equals(savedUser.getEmail())) {
            errors.put("AccountNotFound", "Account doesn't exists");
        }

        if(!loginUserRequestDTO.getPassword().equals(savedUser.getPassword())) {
            errors.put("CheckPassword", "Wrong credentials");
        }

        if(!errors.isEmpty()) {
            throw new LogInFailureException(errors);
        }

        return savedUser;
    }

    @Override
    public List<User> viewUsers() {
        return userRepository.findAll();
    }
}
