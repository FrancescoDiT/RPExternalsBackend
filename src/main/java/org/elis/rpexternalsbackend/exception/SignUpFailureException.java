package org.elis.rpexternalsbackend.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class SignUpFailureException extends RuntimeException{

    public SignUpFailureException(Map<String,String> errors){
        super(errors.toString());
    }
}
