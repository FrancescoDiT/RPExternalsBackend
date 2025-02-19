package org.elis.rpexternalsbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
public class SignInFailureException extends RuntimeException{

    private Map<String,String> errors;

    public SignInFailureException(Map<String,String> errors){
        this.errors=errors;
    }
}
