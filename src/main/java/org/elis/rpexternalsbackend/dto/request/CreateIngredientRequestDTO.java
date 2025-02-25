package org.elis.rpexternalsbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateIngredientRequestDTO {
    @NotBlank(message = "Ingredient name cannot be null")
    private String name;
    @NotBlank(message = "Ingredient image link cannot be null")
    private String imageLink;
    @NotBlank(message = "Ingredient description cannot be null")
    private String description;
    @NotBlank(message = "Ingredient type cannot be null")
    private Boolean frozen;
    private List<Long> allergenIds;
}
