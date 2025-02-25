package org.elis.rpexternalsbackend.exception.controlleradvice;

import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.exception.LogInFailureException;
import org.elis.rpexternalsbackend.exception.SignUpFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LogInFailureException.class)
    public ResponseEntity<String> handleLogInFailureException(LogInFailureException logInFailureException) {
        return handleException("login error", logInFailureException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignUpFailureException.class)
    public ResponseEntity<String> handleSignUpFailureException(SignUpFailureException signUpFailureException) {
        return handleException("signup error", signUpFailureException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DatabaseInconsistencyException.class)
    public ResponseEntity<String> handleDatabaseInconsistencyException(DatabaseInconsistencyException databaseInconsistencyException) {
        return handleException("database inconsistency", databaseInconsistencyException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> handleException(String message, RuntimeException runtimeException, HttpStatus httpStatus) {
        return new ResponseEntity<>(message + ": " + runtimeException.getMessage(), httpStatus);
    }
}
