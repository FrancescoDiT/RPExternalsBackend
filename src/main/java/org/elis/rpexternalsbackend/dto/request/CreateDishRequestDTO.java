package org.elis.rpexternalsbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.elis.rpexternalsbackend.model.value.DishType;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateDishRequestDTO {
    @NotBlank(message = "Dish name cannot be null")
    private String name;
    @NotBlank(message = "Dish image link cannot be null")
    private String imageLink;
    @NotBlank(message = "Dish description cannot be null")
    private String description;
    @NotBlank(message = "Dish type cannot be null")
    private DishType type;
    @NotNull(message = "Dish price cannot be null")
    private List<Long> ingredientIds;
}
