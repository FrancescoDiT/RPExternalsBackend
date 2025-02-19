package org.elis.rpexternalsbackend.controller;

import org.elis.rpexternalsbackend.dto.request.CreateUserRequestDTO;
import org.elis.rpexternalsbackend.dto.request.LoginUserRequestDTO;
import org.elis.rpexternalsbackend.model.User;
import org.elis.rpexternalsbackend.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/all/signup")
    public ResponseEntity<Void> signUp(@RequestBody CreateUserRequestDTO createUserRequestDTO){
        User signedUpUser = userService.signUp(createUserRequestDTO);
        if(signedUpUser != null) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/all/login")
    public ResponseEntity<Void> logIn(@RequestBody LoginUserRequestDTO loginUserRequestDTO){
        User logInUser = userService.login(loginUserRequestDTO);
        if(logInUser != null) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

}
