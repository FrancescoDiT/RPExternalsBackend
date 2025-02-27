package org.elis.rpexternalsbackend.repository;

import org.elis.rpexternalsbackend.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);
    @Query("SELECT i FROM ingredient i JOIN FETCH i.allergens")
    List<Ingredient> findAllIngredientsWithAllergens();
    @Query("SELECT i FROM ingredient i JOIN FETCH i.dishes")
    List<Ingredient> findAllIngredientsWithDishes();
    @Query("SELECT i FROM ingredient i JOIN FETCH i.allergens JOIN FETCH i.dishes")
    List<Ingredient> findAllIngredientsWithAllergensAndDishes();
}
