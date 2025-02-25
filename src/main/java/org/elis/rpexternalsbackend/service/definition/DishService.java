package org.elis.rpexternalsbackend.service.definition;

import org.elis.rpexternalsbackend.dto.request.CreateDishRequestDTO;
import org.elis.rpexternalsbackend.model.Dish;

import java.util.List;

public interface DishService {
    Dish createDish(CreateDishRequestDTO createDishRequestDTO);
    List<Dish> readAllDishes();
    void updateDish();
    void deleteDish();
    Boolean isDishPresent(String dishName);

}
