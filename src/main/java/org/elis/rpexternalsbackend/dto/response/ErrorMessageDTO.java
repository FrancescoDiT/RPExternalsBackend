package org.elis.rpexternalsbackend.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorMessageDTO {

    private LocalDateTime date;
    private Map<String,String> errors;


}
