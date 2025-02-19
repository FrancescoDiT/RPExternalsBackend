package org.elis.rpexternalsbackend.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class LogInFailureException extends RuntimeException{

    private Map<String,String> errors;

    public LogInFailureException(Map<String,String> errors) {
        this.errors = errors;
    }
}