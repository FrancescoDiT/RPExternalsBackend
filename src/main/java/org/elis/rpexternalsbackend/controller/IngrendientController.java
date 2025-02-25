package org.elis.rpexternalsbackend.controller;

import jakarta.validation.Valid;
import org.elis.rpexternalsbackend.dto.request.CreateIngredientRequestDTO;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.elis.rpexternalsbackend.service.definition.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngrendientController {
    @Autowired
    IngredientService ingredientService;

    @PostMapping("/all/ingredients/create")
    public ResponseEntity<Void> createIngredient(@RequestBody @Valid CreateIngredientRequestDTO createIngredientRequestDTO){
        Ingredient ingredient = ingredientService.createIngredient(createIngredientRequestDTO);
        if(ingredient != null) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all/ingredients/readAll")
    public ResponseEntity<List<Ingredient>> readAllIngredients() {
        List<Ingredient> ingredients = ingredientService.readAllIngredients();
        return ResponseEntity.ok(ingredients);
    }
}
