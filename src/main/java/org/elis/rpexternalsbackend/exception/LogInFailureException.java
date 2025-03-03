package org.elis.rpexternalsbackend.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class LogInFailureException extends RuntimeException{

    public LogInFailureException(Map<String,String> errors) {
        super(errors.toString());
    }
}