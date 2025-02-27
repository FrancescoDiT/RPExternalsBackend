package org.elis.rpexternalsbackend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.elis.rpexternalsbackend.dto.request.CreateIngredientRequestDTO;
import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.mapper.CreateIngredientRequestMapper;
import org.elis.rpexternalsbackend.model.Allergen;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.elis.rpexternalsbackend.repository.AllergenRepository;
import org.elis.rpexternalsbackend.repository.IngredientRepository;
import org.elis.rpexternalsbackend.service.definition.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;
    private final AllergenRepository allergenRepository;

    @Override
    public Boolean isIngredientPresent(String ingredientName) {
        Ingredient ingredient = ingredientRepository.findByName(ingredientName.trim());
        return ingredient != null;
    }

    public static Ingredient checkIngredient(Map<String, String> errors, CreateIngredientRequestDTO createIngredientRequestDTO, List<Allergen> allergens, List<Long> allergenIds){

        if(allergens.isEmpty()){
            errors.put("NoAllergensFound", "No allergens found");
        }

        if(allergens.size() != allergenIds.size()){
            errors.put("AllergenNotFound", "Allergen not found");
        }

        if(!errors.isEmpty()){
            throw new DatabaseInconsistencyException(errors);
        }

        return Ingredient.builder()
                .name(createIngredientRequestDTO.getName())
                .imageLink(createIngredientRequestDTO.getImageLink())
                .description(createIngredientRequestDTO.getDescription())
                .frozen(createIngredientRequestDTO.getFrozen())
                .allergens(allergens)
                .build();

    }

    @Override
    public Ingredient createIngredient(CreateIngredientRequestDTO createIngredientRequestDTO) {
        List<Long> allergenIds = createIngredientRequestDTO.getAllergenIds();
        List<Allergen> allergens = allergenRepository.findAllById(allergenIds);

        if(allergenIds.size() != allergens.size()) {
            throw new DataIntegrityViolationException("One or more Allergens were not found");
        }

        Ingredient ingredient = CreateIngredientRequestMapper.INSTANCE.convert(createIngredientRequestDTO);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> createIngredients(List<CreateIngredientRequestDTO> createIngredientRequestDTOS) {
        createIngredientRequestDTOS.forEach(createIngredientRequestDTO -> {
            List<Long> allergenIds = createIngredientRequestDTO.getAllergenIds();
            List<Allergen> allergens = allergenRepository.findAllById(allergenIds);

            if(allergenIds.size() != allergens.size()) {
                throw new DataIntegrityViolationException("One or more Allergens were not found for Ingredient: " + createIngredientRequestDTO.getName());
            }
        });
        List<Ingredient> ingredients = createIngredientRequestDTOS.stream().map(CreateIngredientRequestMapper.INSTANCE::convert).toList();
        return ingredientRepository.saveAll(ingredients);
    }

    @Override
    public List<Ingredient> readAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public List<Ingredient> readAllIngredientsWithAllergens() {
        return ingredientRepository.findAllIngredientsWithAllergens();
    }

    @Override
    public List<Ingredient> readAllIngredientsWithDishes() {
        return ingredientRepository.findAllIngredientsWithDishes();
    }

    @Override
    public List<Ingredient> readAllIngredientsWithAllergensAndDishes() {
        return ingredientRepository.findAllIngredientsWithAllergensAndDishes();
    }

    @Override
    public void updateIngredient() {

    }

    @Override
    public void deleteIngredient() {

    }
}
