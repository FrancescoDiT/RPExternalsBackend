package org.elis.rpexternalsbackend.exception;

import java.util.Map;

public class DatabaseInconsistencyException extends RuntimeException {
    public DatabaseInconsistencyException(Map<String,String> errors) {
        super(errors.toString());
    }
}
