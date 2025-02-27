package org.elis.rpexternalsbackend.service.implementation;

import org.elis.rpexternalsbackend.dto.request.CreateDishRequestDTO;
import org.elis.rpexternalsbackend.exception.DatabaseInconsistencyException;
import org.elis.rpexternalsbackend.model.Dish;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.elis.rpexternalsbackend.repository.DishRepository;
import org.elis.rpexternalsbackend.repository.IngredientRepository;
import org.elis.rpexternalsbackend.service.definition.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishRepository dishRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public Boolean isDishPresent(String dishName) {
        Dish dish = dishRepository.findByName(dishName.trim());
        return dish != null;
    }

    @Override
    public Dish createDish(CreateDishRequestDTO createDishRequestDTO) {

        Map<String, String> errors = new TreeMap<>();
        List<Long> ingredientIds = createDishRequestDTO.getIngredientIds();
        List<Ingredient> ingredients = ingredientRepository.findAllById(ingredientIds);

        if(isDishPresent(createDishRequestDTO.getName())){
            errors.put("DishAlreadyOnDb", "Dish already exists");
        }

        if(ingredients.isEmpty()){
            errors.put("NoIngredientsFound", "No ingredients found");
        }

        if(ingredients.size() != ingredientIds.size()){
            errors.put("IngredientNotFound", "Ingredient not found");
        }

        if(!errors.isEmpty()){
            throw new DatabaseInconsistencyException(errors);
        }

        Dish dish = Dish.builder()
                .name(createDishRequestDTO.getName())
                .imageLink(createDishRequestDTO.getImageLink())
                .description(createDishRequestDTO.getDescription())
                .dishType(createDishRequestDTO.getType())
                .ingredients(ingredients)
                .build();
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> readAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public void updateDish() {

    }

    @Override
    public void deleteDish() {

    }
}
