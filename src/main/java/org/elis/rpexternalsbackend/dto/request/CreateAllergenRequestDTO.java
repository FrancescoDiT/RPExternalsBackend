package org.elis.rpexternalsbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAllergenRequestDTO {
    @NotBlank(message = "Allergen name cannot be blank")
    private String name;
    @NotBlank(message = "Allergen image link cannot be blank")
    private String imageLink;
    @NotBlank(message = "Allergen description cannot be blank")
    private String description;
}
