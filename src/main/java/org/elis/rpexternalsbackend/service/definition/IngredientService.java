package org.elis.rpexternalsbackend.service.definition;

import org.elis.rpexternalsbackend.dto.request.CreateIngredientRequestDTO;
import org.elis.rpexternalsbackend.model.Ingredient;

import java.util.List;

public interface IngredientService {
    //CREATEs
    Ingredient createIngredient(CreateIngredientRequestDTO createIngredientRequestDTO);
    List<Ingredient> createIngredients(List<CreateIngredientRequestDTO> createIngredientRequestDTOList);
    //READs
    List<Ingredient> readAllIngredients();
    List<Ingredient> readAllIngredientsWithAllergens();
    List<Ingredient> readAllIngredientsWithDishes();
    List<Ingredient> readAllIngredientsWithAllergensAndDishes();
    //UPDATEs
    void updateIngredient();
    //DELETEs
    void deleteIngredient();
    Boolean isIngredientPresent(String ingredientName);
}
