package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateUserRequestDTO;
import org.elis.rpexternalsbackend.dto.request.LoginUserRequestDTO;
import org.elis.rpexternalsbackend.exception.LogInFailureException;
import org.elis.rpexternalsbackend.exception.SignInFailureException;
import org.elis.rpexternalsbackend.model.User;
import org.elis.rpexternalsbackend.model.UserType;
import org.elis.rpexternalsbackend.repository.UserRepository;
import org.elis.rpexternalsbackend.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User signUp(CreateUserRequestDTO userRequestDTO) {

        Map<String, String> errors = new TreeMap<String, String>();
        //TODO trim and string fix
        //TODO check email regex
        //TODO check password regex
        if (userRequestDTO == null) {
            errors.put("CreateUserRequest", "request cannot be null");
            throw new SignInFailureException(errors);
        }

        if (userRequestDTO.getName() == null || userRequestDTO.getName().isEmpty()) {
            errors.put("same", "name cannot be empty");
        }

        if (userRequestDTO.getSurname() == null || userRequestDTO.getSurname().isEmpty()) {
            errors.put("surname", "surname cannot be empty");
        }

        if (userRequestDTO.getEmail() == null || userRequestDTO.getEmail().isEmpty()){
            errors.put("email", "email cannot be empty");
        }

        //check for email already exists in db
        assert userRequestDTO.getEmail() != null;
        if (isEmailPresent(userRequestDTO.getEmail())) {
            errors.put("checkEmailEquals", "account with this email already exists");
        }

        if (userRequestDTO.getPassword() == null || userRequestDTO.getPassword().isEmpty()){
            errors.put("password", "password cannot be empty");
        }

        if (userRequestDTO.getRepeatPassword() == null || userRequestDTO.getRepeatPassword().isEmpty()){
            errors.put("repeatPassword", "repeated password cannot be empty");
        }

        //check for passwords to be equals
        assert userRequestDTO.getPassword() != null;
        if (!userRequestDTO.getPassword().equals(userRequestDTO.getRepeatPassword())){
            errors.put("checkPassword", "passwords do not match");
        }
        System.out.print("ffffffffffffffffffffffffffffffffffffffffffffffff"+errors);
        if(!errors.isEmpty()){
            throw new SignInFailureException(errors);
        }

        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setName(userRequestDTO.getName());
        user.setSurname(userRequestDTO.getSurname());
        user.setUserType(UserType.EXTERNAL);
        return userRepository.save(user);

    }

    public boolean isEmailPresent(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;  // Se un utente con quella email esiste, ritorna true
    }

    @Override
    public User login(LoginUserRequestDTO loginUserRequestDTO) {

        Map<String, String> errors = new TreeMap<String, String>();

        if (loginUserRequestDTO == null) {
            errors.put("LoginUserRequest", "request cannot be null");
            throw new LogInFailureException(errors);
        }

        if (loginUserRequestDTO.getEmail() == null || loginUserRequestDTO.getEmail().isEmpty()){
            errors.put("email", "email cannot be empty");
        }

        //check for email already exists in db
        assert loginUserRequestDTO.getEmail() != null;
        if (!loginUserRequestDTO.getEmail().equals(userRepository.findByEmail(loginUserRequestDTO.getEmail()).getEmail())) {
            errors.put("checkEmailEquals", "account doesn't exists");
        }

        if(loginUserRequestDTO.getPassword() == null || loginUserRequestDTO.getPassword().isEmpty()){
            errors.put("password", "password cannot be empty");
        }

        assert loginUserRequestDTO.getPassword() != null;
        if(!loginUserRequestDTO.getPassword().equals(userRepository.findPasswordByEmail(loginUserRequestDTO.getEmail()))){
            errors.put("checkPassword", "wrong credentials");
        }

        if(!errors.isEmpty()){
            throw new LogInFailureException(errors);
        }

        return null;
    }
}
