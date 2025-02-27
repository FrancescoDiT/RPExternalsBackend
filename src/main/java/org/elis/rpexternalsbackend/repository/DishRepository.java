package org.elis.rpexternalsbackend.repository;

import org.elis.rpexternalsbackend.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByName(String name);
    @Query("SELECT d FROM dish as d JOIN FETCH d.ingredients")
    List<Dish> findAllWithIngredients();
}
