package org.elis.rpexternalsbackend.repository;

import org.elis.rpexternalsbackend.model.Allergen;
import org.elis.rpexternalsbackend.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergenRepository  extends JpaRepository<Allergen, Long> {
    Allergen findByName(String name);
}
