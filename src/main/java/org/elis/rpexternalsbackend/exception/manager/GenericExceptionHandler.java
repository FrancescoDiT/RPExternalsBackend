package org.elis.rpexternalsbackend.exception.manager;

import org.elis.rpexternalsbackend.dto.response.ErrorMessageDTO;
import org.elis.rpexternalsbackend.exception.LogInFailureException;
import org.elis.rpexternalsbackend.exception.SignInFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class GenericExceptionHandler {
    @ExceptionHandler(SignInFailureException.class)
    public ResponseEntity<ErrorMessageDTO> manageSignInFailureException(SignInFailureException signInFailureException){
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setDate(LocalDateTime.now());
        errorMessageDTO.setErrors(signInFailureException.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageDTO);
    }

    @ExceptionHandler(LogInFailureException.class)
    public ResponseEntity<ErrorMessageDTO> manageLogInFailureException(LogInFailureException logInFailureException){
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setDate(LocalDateTime.now());
        errorMessageDTO.setErrors(logInFailureException.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageDTO);
    }
}
