package org.elis.rpexternalsbackend.controller;

import jakarta.validation.Valid;
import org.elis.rpexternalsbackend.dto.request.CreateUserRequestDTO;
import org.elis.rpexternalsbackend.dto.request.LoginUserRequestDTO;
import org.elis.rpexternalsbackend.dto.response.ViewUsersDTO;
import org.elis.rpexternalsbackend.model.User;
import org.elis.rpexternalsbackend.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/all/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid CreateUserRequestDTO createUserRequestDTO){
        User signedUpUser = userService.signUp(createUserRequestDTO);
        if(signedUpUser != null) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/all/login")
    public ResponseEntity<Void> logIn(@RequestBody @Valid LoginUserRequestDTO loginUserRequestDTO){
        User logInUser = userService.login(loginUserRequestDTO);
        if(logInUser != null) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all/view")
    public ResponseEntity<List<ViewUsersDTO>> getAllUsers() {
        List<ViewUsersDTO> users = userService.viewUsers();
        return ResponseEntity.ok(users);
    }


}
