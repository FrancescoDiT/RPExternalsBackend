package org.elis.rpexternalsbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateMenuRequestDTO {
    @NotBlank(message = "Menu name cannot be null")
    private String date; //yyyy-MM-dd HH:mm:ss
    @NotNull(message = "Menu description cannot be null")
    private List<Long> dishIds;
}
