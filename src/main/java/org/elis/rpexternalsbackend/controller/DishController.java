package org.elis.rpexternalsbackend.controller;


import jakarta.validation.Valid;
import org.elis.rpexternalsbackend.dto.request.CreateDishRequestDTO;
import org.elis.rpexternalsbackend.model.Dish;
import org.elis.rpexternalsbackend.service.definition.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DishController {
    @Autowired
    DishService dishService;

    @PostMapping("/all/dishes/create")
    public ResponseEntity<Void> createDishes(@RequestBody @Valid CreateDishRequestDTO createDishRequestDTO){
        Dish dish = dishService.createDish(createDishRequestDTO);
        if(dish != null) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all/dishes/readAll")
    public ResponseEntity<List<Dish>> readAllDishes(){
        List<Dish> dishes = dishService.readAllDishes();
        return ResponseEntity.ok(dishes);
    }
}
