package org.elis.rpexternalsbackend.service.definition;

import org.elis.rpexternalsbackend.dto.request.CreateIngredientRequestDTO;
import org.elis.rpexternalsbackend.model.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient createIngredient(CreateIngredientRequestDTO createIngredientRequestDTO);
    List<Ingredient> readAllIngredients();
    void updateIngredient();
    void deleteIngredient();
    Boolean isIngredientPresent(String ingredientName);
}
