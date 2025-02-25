package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateIngredientRequestDTO;
import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.model.Allergen;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.elis.rpexternalsbackend.repository.AllergenRepository;
import org.elis.rpexternalsbackend.repository.IngredientRepository;
import org.elis.rpexternalsbackend.service.definition.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    AllergenRepository allergenRepository;

    @Override
    public Boolean isIngredientPresent(String ingredientName) {
        Ingredient ingredient = ingredientRepository.findByName(ingredientName.trim());
        return ingredient != null;
    }

    @Override
    public Ingredient createIngredient(CreateIngredientRequestDTO createIngredientRequestDTO) {

        Map<String, String> errors = new TreeMap<>();

        if(isIngredientPresent(createIngredientRequestDTO.getName())){
            errors.put("IngredientAlreadyOnDb", "Ingredient already exists");
        }

        List<Allergen> allergens = allergenRepository.findAllById(createIngredientRequestDTO.getAllergenIds());

        if(allergens.isEmpty()){
            errors.put("NoAllergensFound", "No allergens found");
        }

        if(!errors.isEmpty()){
            throw new DatabaseInconsistencyException(errors);
        }

        Ingredient ingredient = Ingredient.builder()
                                          .name(createIngredientRequestDTO.getName())
                                          .imageLink(createIngredientRequestDTO.getImageLink())
                                          .description(createIngredientRequestDTO.getDescription())
                                          .frozen(createIngredientRequestDTO.getFrozen())
                                          .allergens(allergens)
                                          .build();
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> readAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public void updateIngredient() {

    }

    @Override
    public void deleteIngredient() {

    }
}
