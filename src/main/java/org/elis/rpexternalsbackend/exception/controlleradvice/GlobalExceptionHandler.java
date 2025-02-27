package org.elis.rpexternalsbackend.exception.controlleradvice;

import jakarta.validation.ConstraintViolationException;
import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.exception.LogInFailureException;
import org.elis.rpexternalsbackend.exception.SignUpFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LogInFailureException.class)
    public ResponseEntity<String> handleLogInFailureException(LogInFailureException logInFailureException) {
        return handleException("Login error", logInFailureException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignUpFailureException.class)
    public ResponseEntity<String> handleSignUpFailureException(SignUpFailureException signUpFailureException) {
        return handleException("Signup error", signUpFailureException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        return handleException("Database Integrity Violation", dataIntegrityViolationException, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> handleException(String message, RuntimeException runtimeException, HttpStatus httpStatus) {
        return new ResponseEntity<>(message + ": " + runtimeException.getMessage(), httpStatus);
    }
}
